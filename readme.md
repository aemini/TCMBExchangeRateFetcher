# T.C. Merkez Bankası döviz kurları

T.C. Merkez Bankası döviz kurlarını çeken bileşen.

## 1. pom.xml

    <dependency>
        <groupId>com.aryaemini</groupId>
        <artifactId>tcmb-exchange-rate-fetcher</artifactId>
        <version>${version}</version>
    </dependency>


## 2. Gereklilikler
* classpath altında log4j.properties konfigürasyonu bulunmalıdır.

## 3. Kullanım
```java
try {
    TCMBResponse tcmbResponse = TCMBExchangeRateFetcher.fetch();
    System.out.println(tcmbResponse.getBulletinNo());
    System.out.println(tcmbResponse.getDate().toString());
    for(Currency currency : tcmbResponse.getCurrencies()) {
        System.out.println("Code             : " + currency.getCode());
        System.out.println("Unit             : " + currency.getUnit());
        System.out.println("Name             : " + currency.getName());
        System.out.println("Forex Buying     : " + currency.getForexBuying());
        System.out.println("Forex Selling    : " + currency.getForexSelling());
        System.out.println("Banknote Buying  : " + currency.getBanknoteBuying());
        System.out.println("Banknote Selling : " + currency.getBanknoteSelling());
        System.out.println("Cross Rate USD   : " + currency.getCrossRateUsd());
        System.out.println("Cross Rate Other : " + currency.getCrossRateOther());
        System.out.println("");
    }
} catch (ExchangeRateException e) {
    e.printStackTrace();
}
```
