Feature: US1001 kullanici dogru bilgilerle database baglanabilmeli

  @db
  Scenario: TC01 kullanici databe'deki bilgilerle okur

    Given kullanici HMC veri tabanina baglanir
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    And kullanici "Price" sutunundaki verileri okur