package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"/testData/UserData.numbers";
        XLUtility xlUtility = new XLUtility(path);

        int rowNum = xlUtility.getRowCount("Sheet1");
        int colCount = xlUtility.getCellCount("Sheet1", 1);

        String apiData[][] = new String[rowNum][colCount];

        for (int i=1;i<=rowNum;i++){
            for (int j=0;j<colCount;j++){
                apiData[i-1][j] = xlUtility.getCellData("Sheet1", i, 1);
            }
        }

        return apiData;
    }

    @DataProvider(name = "Usernames")
    public String[] getUsernames() throws IOException {
        String path = System.getProperty("user.dir")+"/testData/UserData.numbers";
        XLUtility xlUtility = new XLUtility(path);

        int rowNum = xlUtility.getRowCount("Sheet1");

        String apiData[] = new String[rowNum];

        for (int i=1;i<=rowNum;i++){
            apiData[i-1] = xlUtility.getCellData("Sheet1", i, 1);
        }

        return apiData;
    }
}
