package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.*;
import utils.LogUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private static JsonNode rootNode;

    //🍀🍀 Set Json file path
    public void setJsonFile(String JsonPath) {
        try {
            // Đọc toàn bộ nội dung của file vào một chuỗi
            byte[] jsonData = Files.readAllBytes(Paths.get(JsonPath));
            // Sử dụng ObjectMapper để phân tích chuỗi JSON
            ObjectMapper objectMapper = new ObjectMapper();
            rootNode = objectMapper.readTree(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            rootNode = null;
        }
    }

    //🍀🍀 Get Json VALUE by KEY
    public String getJsonValue(String key) {
        if (rootNode == null) {
            return null;
        }
        JsonNode valueNode = rootNode.path(key);
        return valueNode.asText();
    }

//    🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒

    //🍀🍀 Get json data from ARRAY OBJECT file by KEY
    public Object[][] getJsonArrayObjectFile(String filePath, String arrayKey) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);   //phân tích file và trả về đối tượng JsonElement.
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(arrayKey); // Get arrayKey
                return convertJsonArrayToObjectArray(jsonArray);    //Chuyển đổi JsonArray thành Object[][]
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    //Chuyển đổi mảng JSON thành Object[][]
    private Object[][] convertJsonArrayToObjectArray(JsonArray jsonArray) {
        List<Object[]> rows = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                List<Object> row = new ArrayList<>();
                //Duyệt qua từng key của JsonObject, lấy String value và add list row
                for (String key : jsonObject.keySet()) {
                    row.add(jsonObject.get(key).getAsString());
                }
                rows.add(row.toArray()); // convert to Array, and add rows
            }
        }
        return rows.toArray(new Object[0][0]); // convert to Object[][]
    }

    //🍀🍀 Get json data from ARRAY OBJECT file with startRow-endRow by arrayKEY
    public Object[][] getJsonArrayObjectFileWithSpecifiedRow(String filePath, String arrayKey, int startRow, int endRow) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(arrayKey);
                LogUtils.info("Get data json Array Object file from row " + startRow + " to row " + endRow);
                return convertJsonArrayToObjectArray(jsonArray, startRow, endRow);
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    private Object[][] convertJsonArrayToObjectArray(JsonArray jsonArray, int startRow, int endRow) {
        List<Object[]> rows = new ArrayList<>();
        // Chuyển đổi startRow và endRow từ 1-based index sang 0-based index
        startRow -= 1;
        endRow -= 1;

        for (int i = startRow; i <= endRow && i < jsonArray.size(); i++) {
            JsonElement element = jsonArray.get(i);
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                List<Object> row = new ArrayList<>();
                for (String key : jsonObject.keySet()) {
                    row.add(jsonObject.get(key).getAsString());
                }
                rows.add(row.toArray());
            }
        }
        return rows.toArray(new Object[0][0]); // Chuyển đổi rows thành Object[][]
    }

//    🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒

    //🍀🍀 Lấy data json Object file
    public Object[][] getJsonObjectFile(String jsonPath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(jsonPath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            return convertJsonObjectToObjectArray(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    private Object[][] convertJsonObjectToObjectArray(JsonObject jsonObject) {
        Object[][] data = new Object[1][jsonObject.size()];
        int index = 0;
        for (String key : jsonObject.keySet()) {
            data[0][index++] = jsonObject.get(key).getAsString();
        }
        return data;
    }

    //🍀🍀 Lấy data json Object file theo row chỉ định (1-based)
    public Object[][] getJsonObjectFileWithSpecifiedRow(String jsonPath, int startRow, int endRow) {
        // Kiểm tra tính hợp lệ của các chỉ số dòng
        if (startRow <= 0 || endRow <= 0 || startRow > endRow) {
            LogUtils.error("Invalid row indices: startRow=" + startRow + ", endRow=" + endRow);
            return new Object[0][0]; // Trả về mảng rỗng nếu chỉ số không hợp lệ
        }
        try (FileReader reader = new FileReader(jsonPath)) {
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            Object[][] data = extractData(jsonObject, startRow - 1, endRow - 1); // Chuyển đổi sang 0-based
            LogUtils.info("Get data json Object file from row " + startRow + " to row " + endRow);
            return data;
        } catch (IOException e) {
            LogUtils.error("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0]; // Trả về mảng rỗng trong trường hợp lỗi
        }
    }

    // Trích xuất dữ liệu từ một đối tượng JSON và trả về Object[][]
    private Object[][] extractData(JsonObject jsonObject, int startRow, int endRow) {
        //Get list keys of Json Object and convert to a String Array
        String[] keys = jsonObject.keySet().toArray(new String[0]);

        if (startRow >= 0 && endRow < keys.length && startRow <= endRow) {
            Object[][] data = new Object[1][endRow - startRow + 1]; // Object[][] chứa extract data
            for (int i = startRow; i <= endRow; i++) {
                data[0][i - startRow] = jsonObject.get(keys[i]).getAsString(); //Lấy giá trị tương ứng với KEY và lưu vào mảng
            }
            return data;
        } else {
            LogUtils.error("Invalid indices: startRow=" + (startRow + 1) + ", endRow=" + (endRow + 1));
            return new Object[0][0]; // Trả về mảng rỗng nếu chỉ số không hợp lệ
        }
    }


}

