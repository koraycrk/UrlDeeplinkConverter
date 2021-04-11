# UrlDeeplinkConverter
Bu web servis, mobil ve web uygulamaları arasındaki xyz.com bağlantılarını dönüştürür.

Programlama dili olarak Java (Spring Boot) kullanılmıştır.
Veri tabanı olarak MySql kullanılmıştır.

İki adet end point bulunmaktadı.

1- http://localhost:8080/url-to-deeplink POST
Parametre, Request Body içerisinde Json formatında "Url" parametresi ile geçilmektedir.
Parametre formatı xyz.com/{CustomerNumber-or- CitizenshipNumber}/{TransactionName}-t-{ ContentId}? FlowName={flowName} şeklindedir. FlowName parametresi isteğe bağlıdır.

2- http://localhost:8080/deeplink-to-url POST
Parametre, Request Body içerisinde Json formatında "Url" parametresi ile geçilmektedir.
Parametre formatı ty://?Page=Transaction&ContentId={ ContentId}&flowId={flowId} şeklindedir. flowId parametresi isteğe bağlıdır.

Her iki servisin Reponse'u da aynı tiptedir.
"result": Üretilen url veya deeplink bilgisi bu alanda dönmektedir.
"header.code": Servis sonucunun kodunu dönemktedir.
"header.message": Servisin hatalı sonlanması durumunda ilgili hataya ait maesajı dönmektedir.
"header.success": Servisin başarılı sonlanıp sonlanmadığını dönmektedir.

Servis yapılan her bir isteğe ait request ve response MySql üzerindeki "Log" tablosunda saklanmaktadır.

Servisi test edebilmek adına veritabanına tek bir kayıt atılmıştır. Bu kayda ait alanlar:
CustomerNumber: 5632
TransactionName: eft
ContentId: 123
flowName: TestEftFlow
flowId: 352

