package com.shamsheev.wildberries.api.statistics.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles


@DataJpaTest
@ActiveProfiles("test")
internal class OrderRepositoryImplTest {

    @Autowired
    private lateinit var repository: StockRepository

    @Test
    fun saveAndGetById() {
//        //given
//        val product = Product(2106904170120128, "11619197", 11619197, "Одежда", "Блузки", "Luxury Plus", "60")
//
//        repository.save(product)
//        //when
//        val result = repository.getProductById(product.id).get()
//        //then
//        assertEquals(product, result)
    }
//
//    @Test
//    fun getAll() {
//        //given
//        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
//        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
//        repository.save(note1)
//        repository.save(note2)
//        //when
//        val result = repository.findAll()
//        //then
//        assertContentEquals(listOf(note1, note2), result)
//    }
//
//    @Test
//    fun searchByName() {
//        //given
//        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
//        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
//        repository.save(note1)
//        repository.save(note2)
//        //when
//        val result = repository.searchNoteByName("name2")
//        //then
//        assertEquals(listOf(note2), result)
//    }
//
//    @Test
//    fun searchByAddress() {
//        //given
//        val note1 = Note(name = "name1", address = "address", phone = "phone1")
//        val note2 = Note(name = "name2", address = "address", phone = "phone2")
//        repository.save(note1)
//        repository.save(note2)
//        //when
//        val result = repository.searchNoteByAddress("address")
//        //then
//        assertEquals(listOf(note1, note2), result)
//    }
//
//    @Test
//    fun searchByPhone() {
//        //given
//        val note1 = Note(name = "name1", address = "address", phone = "phone1")
//        val note2 = Note(name = "name2", address = "address", phone = "phone2")
//        repository.save(note1)
//        repository.save(note2)
//        //when
//        val result = repository.searchNoteByPhone("phone3")
//        //then
//        assertEquals(listOf(), result)
//    }
//
//    @Test
//    fun deleteById() {
//        //given
//        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
//        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
//        repository.save(note1)
//        repository.save(note2)
//        //when
//        repository.deleteById(note1.id!!)
//        //then
//        val result = repository.findAll()
//        assertContentEquals(listOf(note2), result)
//    }
//
//    @Test
//    fun updateById() {
//        //given
//        val note1 = Note(name = "name1", address = "address1", phone = "phone1")
//        repository.save(note1)
//        //when
//        val note2 = Note(name = "name2", address = "address2", phone = "phone2")
//        note2.id = note1.id
//        repository.save(note2)
//        //then
//        val result = repository.getNoteById(note1.id!!)
//        assertEquals(note2, result)
//    }
}

/*

2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:06:48, lastChangeDate=2024-01-19T18:05:06, warehouseName=Тула, countryName=россия, oblastOkrugName=южный федеральный округ, regionName=Краснодарский край, supplierArticle=116914, nmId=189483567, barcode=21116914170112120, category=Одежда, subject=Пуховики, brand=Luxury Plus, techSize=56, incomeID=15986564, isSupply=false, isRealization=true, totalPrice=20000, discountPercent=25, spp=20, finishedPrice=12000, priceWithDisc=15000, isCancel=false, cancelDate=0001-01-01T00:00, orderType=Клиентский, sticker=16011518157, gNumber=93074259485198397232, srid=5ad54bfc63544ea98681fcdf93e642b0)
2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:06:48, lastChangeDate=2024-01-23T01:19:27, warehouseName=Коледино, countryName=россия, oblastOkrugName=южный федеральный округ, regionName=Краснодарский край, supplierArticle=116923, nmId=189584264, barcode=21116923170116124, category=Одежда, subject=Пуховики, brand=Luxury Plus, techSize=58, incomeID=15865662, isSupply=false, isRealization=true, totalPrice=20000, discountPercent=25, spp=20, finishedPrice=12000, priceWithDisc=15000, isCancel=true, cancelDate=2024-01-22T00:00, orderType=Клиентский, sticker=14747117704, gNumber=93074259485198397232, srid=a9617a19bd624c3f9914265b58c7ac3b)
2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:22:48, lastChangeDate=2024-01-19T22:34:27, warehouseName=Казань, countryName=россия, oblastOkrugName=дальневосточный федеральный округ, regionName=Приморский край, supplierArticle=117327, nmId=35233188, barcode=15117327170120128, category=Одежда, subject=Куртки, brand=Luxury Plus, techSize=60, incomeID=15327826, isSupply=false, isRealization=true, totalPrice=15000, discountPercent=43, spp=20, finishedPrice=6840, priceWithDisc=8550, isCancel=false, cancelDate=0001-01-01T00:00, orderType=Клиентский, sticker=14524167724, gNumber=97588695561949788014, srid=5063046909319992947.0.0)
2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:29:09, lastChangeDate=2024-01-19T20:27:05, warehouseName=Электросталь, countryName=россия, oblastOkrugName=приволжский федеральный округ, regionName=Республика Башкортостан, supplierArticle=134713, nmId=171538808, barcode=15134713170124132, category=Одежда, subject=Куртки, brand=Luxury Plus, techSize=62, incomeID=14542373, isSupply=false, isRealization=true, totalPrice=14000, discountPercent=30, spp=20, finishedPrice=7840, priceWithDisc=9800, isCancel=false, cancelDate=0001-01-01T00:00, orderType=Клиентский, sticker=13803551187, gNumber=2514964649775949314, srid=5432006189857660586.0.0)
2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:32:16, lastChangeDate=2024-01-23T01:19:41, warehouseName=Электросталь, countryName=россия, oblastOkrugName=приволжский федеральный округ, regionName=Самарская область, supplierArticle=132601, nmId=79829278, barcode=3132601170128136, category=Одежда, subject=Брюки, brand=Luxury Plus, techSize=64, incomeID=12083447, isSupply=false, isRealization=true, totalPrice=8000, discountPercent=55, spp=20, finishedPrice=2880, priceWithDisc=3600, isCancel=true, cancelDate=2024-01-22T00:00, orderType=Клиентский, sticker=16020530898, gNumber=2087585615438884373, srid=45734261087811367.3.0)
2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:32:16, lastChangeDate=2024-01-19T16:23:04, warehouseName=Белая дача, countryName=россия, oblastOkrugName=приволжский федеральный округ, regionName=Самарская область, supplierArticle=132601, nmId=79829278, barcode=3132601170124132, category=Одежда, subject=Брюки, brand=Luxury Plus, techSize=62, incomeID=7547218, isSupply=false, isRealization=true, totalPrice=8000, discountPercent=55, spp=20, finishedPrice=2880, priceWithDisc=3600, isCancel=false, cancelDate=0001-01-01T00:00, orderType=Клиентский, sticker=10200276493, gNumber=2087585615438884373, srid=45734261087811367.4.0)
2024-01-24 00:28:13.989  INFO 2932 --- [nio-8080-exec-5] c.s.w.a.s.adapter.WbStatisticsAdapter    : OrdersItem(date=2024-01-19T15:32:16, lastChangeDate=2024-01-23T01:19:41, warehouseName=Казань, countryName=россия, oblastOkrugName=приволжский федеральный округ, regionName=Самарская область, supplierArticle=107827, nmId=12192603, barcode=3107827170132140, category=Одежда, subject=Брюки, brand=Luxury Plus DISKONT, techSize=66, incomeID=8359346, isSupply=false, isRealization=true, totalPrice=5500, discountPercent=63, spp=20, finishedPrice=1628, priceWithDisc=2035, isCancel=true, cancelDate=2024-01-22T00:00, orderType=Клиентский, sticker=12019713402, gNumber=2087585615438884373, srid=45734261087811367.5.0)
 */