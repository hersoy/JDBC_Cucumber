package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;

import java.sql.SQLException;

public class HMC_DBUtils_stepDefinition {

    @Given("kullanici DBUtils ile HMC veri tabanina baglanir")
    public void kullanici_db_utils_ile_hmc_veri_tabanina_baglanir() {
        DBUtils.getConnection();

    }
    @Given("kullanici DBUtils ile {string} tablosundaki {string} verilerini alir")
    public void kullanici_db_utils_ile_tablosundaki_verilerini_alir(String table, String field) {
        String query = "SELECT "+field+" FROM "+ table;
        DBUtils.executeQuery(query);

    }
    @Given("kullanici DBUtils ile {string} sutunundaki verileri okur")
    public void kullanici_db_utils_ile_sutunundaki_verileri_okur(String field) throws SQLException {
        DBUtils.getResultset().first();
        System.out.println(DBUtils.getResultset().getString(field));

    }
    @And("DBUtil ile tum {string} degerlerini sira numarasi ile yazdirir")
    public void dbutilIleTumDegerleriniSiraNumarasiIleYazdirir(String field) throws SQLException {
        // 1. derste while loop ile next() kullanarak liste yazdirmistik
        // şimdi de for loop ile yardirakim
        DBUtils.getResultset().last();
        int sonSatirNo= DBUtils.getResultset().getRow();
        DBUtils.getResultset().first();

        for (int i = 1; i <sonSatirNo ; i++) {
            System.out.println(i+". kayit "+DBUtils.getResultset().getString(field));
            DBUtils.getResultset().next();

        }

    }

    @Then("DBUtill ile {int}. {string} in {int} oldugunu test eder")
    public void dbutillIleInOldugunuTestEder(int istenenSiraNo, String field, int expectedDeger) throws SQLException {
        DBUtils.getResultset().absolute(istenenSiraNo);
        double actuelDeger=DBUtils.getResultset().getDouble(field);

        System.out.println("expectedDeger = " + expectedDeger);
        System.out.println("actuelDeger = " + actuelDeger);

        Assert.assertTrue(actuelDeger==expectedDeger);

    }

    @Then("tHOTEL tablosunda IDHotel degeri {int} olan kaydin Email bilgisini {string} yapar")
    public void thotelTablosundaIDHotelDegeriOlanKaydinEmailBilgisiniYapar(int idHotel, String yeniEmail) {

        String updateQuery="update tHOTEL SET Email = '"+yeniEmail+"' WHERE IDHotel="+idHotel+";";
    }
}
