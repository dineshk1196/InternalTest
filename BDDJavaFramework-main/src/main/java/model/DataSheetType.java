package model;

public enum DataSheetType {
    USERS("Users");

    String sheetName;

    DataSheetType(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetName() {
        return sheetName;
    }
}
