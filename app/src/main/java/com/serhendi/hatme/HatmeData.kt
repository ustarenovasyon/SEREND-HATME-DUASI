package com.serhendi.hatme

data class HatmePart(val number:Int, val text:String)
data class HatmeLesson(val title:String, val parts:List<HatmePart>)

val lessons = listOf(
    HatmeLesson("Besmele, hamd ve salât", listOf(
        HatmePart(1,"BİSMİLLAHİRRAHMANİRRAHİM"),
        HatmePart(2,"Elhamdü lillâhi rabbil âlemin Elhamdü lillâhi hakka hamdihi ve senâihi vessalâtü vesselâmü alâ hayri halkıhi Muhammedin ve alâ âlihi ve sahbihi ecmain")
    )),
    HatmeLesson("Hatme sevabının hediye edilmesi", listOf(
        HatmePart(3,"Allahümme belliğ ve evsıl misle sevâbi hâzihil hatmetiş şerifetil mübâreketi badel Gabüli minnâ bil fadli vel keremi hediyyeten minnâ vâsıleten ilâ ravdati menbaıs sıdkı vessafâ eşrafil verâ Hazreti Seyyidinâ Muhammeden il Mustafa (Sallallahu Aleyhi ve Sellem) (ve ilâ rühi)"),
        HatmePart(4,"Küllin min âlihi ve evlâdihi ve ezvâcihi ve ashâbihi ve etbâihi ve zürriyyâtihi ve muhâcirihi ve ensârih (rıdvanullahi teâlâ aleyhim ecmain)")
    )),
    HatmeLesson("Tarikat silsilesine giriş ve Şâh-ı Nakşibend", listOf(
        HatmePart(5,"Küllin min sâdâti silsiletit tarikatil aliyyetin Nakşibendiyyeti vel Kâdiriyyeti, ves Sühreverdiyyeti vel Çeştiyyeti vel Kübreviyye"),
        HatmePart(6,"Şeyhınâ ve melâzinâ ve kıdvetinâ ve imâminâ ve imâmit tarikati zil feydıl câri vennüris sâri eş şeyh bahâil hakkı vel hakikati veddin hazreti eş şeyh Muhammedinil Üveysiyyil Buhâri el marüfi bi Şâh-ı Nakşibend"),
        HatmePart(7,"Menbail meârifi vel kemâli seyyidis sâdâti es Seyyid Emir Külâl")
    )),
    HatmeLesson("Hâcegân büyükleri", listOf(
        HatmePart(8,"El Mukbili aleyke ve limâsivâken nâsi eş Şeyh Muhammed Babassemmâsi"),
        HatmePart(9,"El vâlihi fi mehabbeti mevlâhül ganiyyi el marüfi bi Hazreti azizân Hoca Aliyyir Râmiteni"),
        HatmePart(10,"El Muridi anil murâdid dünyeviyyi vel uhreviyyi Hazreti eş Şeyh Mahmüdinil İnciri Fağnevi"),
        HatmePart(11,"El Mütesellihi anil hicâbil beşeriyyi Hazreti eş şeyh Ârifir Rivegeri")
    )),
    HatmeLesson("İmam-ı Rabbânî ve Mevlânâ Hâlid", listOf(
        HatmePart(12,"Gutbil evliyei ve burhânil esfiyei kâmiil bidati muhyissünneti şeyhil meşâyihi mevlânâ Hazreti eş Şeyh Abdülhalık Gucdevâni"),
        HatmePart(13,"Gutbil haggâni el gavsis samedâni eş Şeyh Ahmed el Fârükıyyis serhendi el marüfi bil İmâmir Rabbâni müceddidil elfisseni")
    )),
    HatmeLesson("Seyyid Abdullah ve Seyyid Tâhâ", listOf(
        HatmePart(14,"Gutbi dâiretil irşâdi Gavsis sekaleyni ales sedâdi essâiri fillâhir râkis sâcidi zilcenâheyni Diyeiddin, Mevlânâ Hazreti eş şeyh Hâlid"),
        HatmePart(15,"Menbail hılmi ve nüriz zalâm elhâdi beynel aşeiri vel eğvâm sirâciddinillezi zahera min halefi seyyidil enâm mevlânâ Hazreti es Seyyid Abdullah")
    )),
    HatmeLesson("Sıbğatullah Arvasî ve Abdurrahman Tâhî", listOf(
        HatmePart(16,"Şeyhınel gayürillezi bihi netebehel veguri gutbil irşâdi vel mederi hazreti şihebiddin mevlana Hazreti eş şeyh es Seyyid Tâhâ"),
        HatmePart(17,"Sultânil küberâil mütegaddimin gıdvetil küberâil müteahhirin gavsil âmmeti vel hâifin gutbil eimmeti ves sâlikin muğisil müsteğisin münisil gurabei vel âşıkin şeyhinel kâmilil mükemmilil Üveysiyyi Mevlânâ Hazreti eş şeyh seyyid Sıbğatullahil Arvesi")
    )),
    HatmeLesson("Fethullah ve Muhammed Diyâüddin", listOf(
        HatmePart(18,"Sultânil arifin, kutbil aktâbil vâsılin, el müteşerrifi bil fenâil mutlak mürebbis sâlikine ilâ rabbihim alel vechil ehak, nâsıriş şeriatil garrâ kâmiil bid'atid darrâ müceddidi âsâris selefi vet tâbiin ve mümehhidi bünyâni tarikatil halefi vellâhikin el mutasarrifi alel ıtlak ellezi lem yüra lehü nâzirun badet tefehhusı fil âfâk, kâtıın nisbeti anil mübtedittâği mevlânâ şeyhinel kâmilil mükemmili, Hazreti eş şeyh Abdurrahmani Tâhi"),
        HatmePart(19,"Şeyhiş şeriati ve şehbâzit tarikati ve burhânil hakikati, elfâni fillâhi ve bâki billâh el mutasımi bi hablillâh şeyhinel kâmili mükemmili, mevlânâ Hazreti eş Şeyh Fethullah")
    )),
    HatmeLesson("Ahmed el-Haznevî", listOf(
        HatmePart(20,"Câmii kemâlâtil evliyâil evvelin ve mecmail âdâbi ve füyüdâtil âhirin, umdetil İslâmi vel müslimin, umüdil meşâyihı bi ecmaihim ves sâlikinn nurus semâvâti vel aradin sirâcil milleti ved din, kehfid duafâi vel mesâkin, kutbil eimmeti ves sâlikin sultânil âşıkin şeyhinel kâmilil mükemmili mevlânâ Hazreti eş-Şeyh Muhammed Diyâüddin")
    )),
    HatmeLesson("Abdülhakim el-Hüseynî", listOf(
        HatmePart(21,"Vârisi makâmâtil evliyâi vel mürselin, imâmil mü'minin, umdetil âbidine ves sâlikin muzhiriş şeriatil ğarrâi muhyit tarikatin Nakşibendiyyetil beydâ el mütesellihi anil hicâbil insiyyi el hâzini lissırril maneviyyi mevlânâ şeyhinel kâmilil mükemmili Hazreti eş şeyh Ahmed el Haznevi")
    )),
    HatmeLesson("Muhammed Râşid ve Abdülbâkî el-Hüseynî", listOf(
        HatmePart(22,"Sultânil câzibin nüri hidâyetil vasılin kutbil ferdi lil âlemin, nâşiril meslekil Ahmediyyeti fer ış şeceretil Muhammediyye pirit tarikatin Nakşibendiyye, sâhibis seciyyetil mahmüdiyye es sâki min hıyâdıl bahâiyye nâsıbil alamid diniyye muhyi âsâri kübbâris selefi vet tâbiin metlail himemi bilyakin menhecis saâdeti lil musaddıkin şeyhinel kâmilil mükemmilil üveysiyyi Bilvânisi Mevlânâ Hazreti eş şeyh es-Seyyid Abdülhakim EL-Hüseyni"),
        HatmePart(23,"Sultânil müslimine vel müstecirin ve tâcil mensürin ve muhibbil mahbübin ve müşâril müsteşirin ve irşâdil mürşidin ve Sırris sâdıkine bi hidâyeti rabbil alemin el fâtihi künüzel ilmi veddin el müstakırri biş şeriatil garrai muhyit tarikatin Nakşibendiyyetil beydâ şeyhinel kâmilil mükemmilil Bilvânisi Mevlânâ Hazreti eş şeyh es Seyyid Muhammed Râşid Hüseyni")
    )),
    HatmeLesson("Muhammed Sâkî ve kapanış duaları", listOf(
        HatmePart(24,"Mecmai âmâlil müslimin kutbil fâizine sikkatil müttakine vesiletil mütevekkilin sâhibis sehâveti vel kerâmeti lil âlemin kesîril mehabbeti lil mütevâdiin sâhibiş şeriati vet tarikatin Nakşibendiyye şeyhinel kâmili mükemmilil üveysiyyil Bilvânisi, Mevlânâ Hazreti eş şeyh es Seyyid Abdülbaki EL-Hüseyni"),
        HatmePart(25,"Sultanin âşikin Kutbil aktabil vasilin El mutemessikil bi şeriati seyyidil mürselin Kesiril mahabbeti lissadikin Melceil ûlemai vel arifin, Cazibi kulübil müslimin, muhyis sünnetin nebeviyyeti muceddidil adabi vet tarikatin Nakşabendiyyetil âliyyeti elmensuri min şemsil medinetil münevvereti Mevlaya ve şeyhi ve seyyidi ve senedi ve men bihi temessuki ve aleyhi itimadi ve bihi iftihari ve minhu istimdadi ve kurrati ayni Şeyhinel kamilil mukemmilil bilvanisi mevlana Hazreti Eş şeyh Seyyid Muhammed Saki El-Hüseyni"),
        HatmePart(26,"Küllin mines sâdâti vel hulefâi vel müridine vel muhibbine vel mahbübine vel mensübine vel müntesibine ilâ hâzihittarikatil aliyyeti ve sâirit turuk."),
        HatmePart(27,"Allâhümmec al misle sevabihâ mektüben fisahifeti amâli küllin verfa bihâ deracâti küllin ve ali bihâ fi alâ illiyyine menzilete küllin, ve zidnâ bihâ mehabbeten inde cenâbi küllin, ve efid aleynâ min berekâti küllin, ve etmim lenâ sülüke hâzihit tarikatil aliyyeti ve veffiknâ limerdâti şeyhinâ ve imtisâli evâmirihi ve ictinâbi menâhih"),
        HatmePart(28,"Allâhümmerzuknel bekâe bike badel fenai fike alâ kademi sâdâtines sâlikine fihâ"),
        HatmePart(29,"Allâhümmağfir lenâ, hatâyânâ ve eclibnâ ilâ mehabbetike bi-mehabbeti habibike ve bimehabbeti evliyaike verzuknet tevfika vel istikamete ala dinike ve taatike bi rahmetike Yâ Erhamerrâhimin"),
        HatmePart(30,"Âmin, Vel hamdü lillâhi rabbil âlemin.")
    ))
)
