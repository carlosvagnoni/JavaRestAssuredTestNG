package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

/**
 * Provides test data to other test classes through TestNG DataProvider annotations.
 */
public class DataProviders {

    /**
     * Retrieves all data from an Excel file and formats it into a two-dimensional array.
     * @return A two-dimensional array containing all data from the Excel file.
     * @throws IOException if there's an issue accessing or reading the Excel file.
     */
    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");
        int colcount=xl.getCellCount("Sheet1",1);

        String apidata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }

    /**
     * Retrieves usernames from an Excel file and formats them into a one-dimensional array.
     * @return A one-dimensional array containing usernames from the Excel file.
     * @throws IOException if there's an issue accessing or reading the Excel file.
     */
    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");

        String apidata[]=new String[rownum];
        for(int i=1;i<=rownum;i++)
        {
            apidata[i-1]=xl.getCellData("Sheet1", i, 1);
        }

        return apidata;

    }
}

