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
import java.util.Map;
import java.util.Map.Entry;

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
                return convertJsonArrayToObjectArray(jsonArray); // convert Json Array to Object Array
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    //🍀🍀 Get json data from ARRAY OBJECT file with startRow-endRow by arrayKEY
    public Object[][] getJsonArrayObjectFileWithSpecifiedRow(String filePath, String arrayKey, int startRow, int endRow) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(arrayKey);
                LogUtils.info("Get data json Array Object file from row " + startRow + " to row " + endRow);
                return convertToJsonObjectArray(jsonArray, startRow, endRow);   //convert Json Array to Object Array
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

//    🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒

    //🍀🍀 Get data json Object file
    public Object[][] getJsonObjectFile(String jsonPath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(jsonPath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            return convertJsonObjectToObjectArray(jsonObject, false);   //convert Json Object to Object Array
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    //🍀🍀 Lấy data json Object file theo row chỉ định (1-based)
    public Object[][] getJsonObjectFileWithSpecifiedRow1(String jsonPath, int startRow, int endRow) {
        // Kiểm tra tính hợp lệ của các chỉ số dòng
        if (startRow <= 0 || endRow <= 0 || startRow > endRow) {
            LogUtils.error("Invalid row indices: startRow=" + startRow + ", endRow=" + endRow);
            return new Object[0][0]; // Trả về mảng rỗng nếu chỉ số không hợp lệ
        }
        try (FileReader reader = new FileReader(jsonPath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            Object[][] data = convertToJsonObjectArray(jsonElement, startRow, endRow); // convert Json Object to JsonObject[][]
            LogUtils.info("Get data json Object file from row " + startRow + " to row " + endRow);
            return data;
        } catch (IOException e) {
            LogUtils.error("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0]; // Trả về mảng rỗng trong trường hợp lỗi
        }
    }

//    🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒

    //🍀🍀 Get data Json NEST OBJECT file
    public Object[][] getJsonNestObjectFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);   // Phân tích file và trả về đối tượng jsonElement.
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                return convertJsonObjectToObjectArray(jsonObject, true);    //convert Json Object to Array Object
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    //    🍀🍀getJsonNestObjectFileWithSpecifiedRow
    public Object[][] getJsonNestObjectFileWithSpecifiedRow(String filePath, int startRow, int endRow) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                List<JsonObject> rows = new ArrayList<>();
                int currentRow = 0;
                for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    if (currentRow >= startRow && currentRow <= endRow) {
                        rows.add(entry.getValue().getAsJsonObject());
                    }
                    currentRow++;
                }
                return convertJsonObjectListToObjectArray(rows);
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    //🍀🍀Get data Json NEST OBJECT file with Specified key (1 key)
    public Object[][] getJsonNestObjectFileWithSpecifiedKey(String filePath, String key) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                LogUtils.info("Getting data for key: " + key);
                return convertJsonObjectToObjectArray(jsonObject, new String[]{key});
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    //    🍀🍀 Get data Json NEST OBJECT file with specified keys ( 2 keys or more)
    public Object[][] getJsonNestObjectFileWithSpecifiedKeys(String filePath, String[] keys) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                LogUtils.info("Getting data for keys: " + String.join(", ", keys));
                return convertJsonObjectToObjectArray(jsonObject, keys);
            } else {
                throw new IllegalArgumentException("JSON file is not a valid object");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

//    🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒🍒

    //    🍀🍀 Convert Json Object to Object[][]
    private Object[][] convertJsonObjectToObjectArray(JsonObject jsonObject, String[] keys) {
        List<Object[]> rows = new ArrayList<>();
        for (String key : keys) {
            if (jsonObject.has(key)) {
                JsonObject innerObject = jsonObject.getAsJsonObject(key);
                List<Object> row = new ArrayList<>();
                for (String innerKey : innerObject.keySet()) {
                    row.add(innerObject.get(innerKey).getAsString());
                }
                rows.add(row.toArray());
            }
        }
        return rows.toArray(new Object[0][0]);
    }

    //    🍀🍀  Convert Json Object to Object[][]
    private Object[][] convertJsonObjectToObjectArray(JsonObject jsonObject, boolean isNestObject) {
        List<Object[]> rows = new ArrayList<>();
        if (isNestObject) {
            for (String key : jsonObject.keySet()) {
                JsonObject innerObject = jsonObject.getAsJsonObject(key);
                List<Object> row = new ArrayList<>();
                for (String innerKey : innerObject.keySet()) {
                    row.add(innerObject.get(innerKey).getAsString());
                }
                rows.add(row.toArray());
            }
        } else {
            Object[][] data = new Object[1][jsonObject.size()];
            int index = 0;
            for (String key : jsonObject.keySet()) {
                data[0][index++] = jsonObject.get(key).getAsString();
            }
            return data;
        }
        return rows.toArray(new Object[0][0]);
    }

    //    🍀🍀 Convert Json Array to Object[][]
    private Object[][] convertJsonArrayToObjectArray(JsonArray jsonArray) {
        List<Object[]> rows = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                List<Object> row = new ArrayList<>();
                for (String key : jsonObject.keySet()) {
                    row.add(jsonObject.get(key).getAsString());
                }
                rows.add(row.toArray());
            }
        }
        return rows.toArray(new Object[0][0]);
    }

    //    🍀🍀 Convert Json Object list to Object Array
    private Object[][] convertJsonObjectListToObjectArray(List<JsonObject> jsonObjectList) {
        Object[][] data = new Object[jsonObjectList.size()][1];
        for (int i = 0; i < jsonObjectList.size(); i++) {
            data[i][0] = jsonObjectList.get(i);
        }
        return data;
    }

    // 🍀🍀 Convert to Json Object Array
    private Object[][] convertToJsonObjectArray(JsonElement jsonElement, int startRow, int endRow) {
        List<Object[]> rows = new ArrayList<>();
        // Chuyển đổi startRow và endRow từ 1-based index sang 0-based index
        startRow -= 1;
        endRow -= 1;

        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
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
        } else if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String[] keys = jsonObject.keySet().toArray(new String[0]);
            if (startRow >= 0 && endRow < keys.length && startRow <= endRow) {
                Object[][] data = new Object[1][endRow - startRow + 1];
                for (int i = startRow; i <= endRow; i++) {
                    data[0][i - startRow] = jsonObject.get(keys[i]).getAsString(); //Lấy giá trị tương ứng với KEY và lưu vào mảng
                }
                return data;
            } else {
                LogUtils.error("Invalid indices: startRow=" + (startRow + 1) + ", endRow=" + (endRow + 1));
                return new Object[0][0]; // Trả về mảng rỗng nếu chỉ số không hợp lệ
            }
        }
        return rows.toArray(new Object[0][0]); // Chuyển đổi rows thành Object[][]
    }

}

