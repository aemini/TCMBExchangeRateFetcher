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
TCMBExchangeRates fetcher = new TCMBExchangeRates();

try {
    List<ExchangeRate> rateList = fetcher.fetch();
} catch (ExchangeRateException e) {
    e.printStackTrace();
}
```
