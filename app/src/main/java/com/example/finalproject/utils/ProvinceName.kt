package com.example.finalproject.utils

import com.example.finalproject.preference.CodeProvince

object nameProvince {
    fun getnameProvince(region:String?):String{
      return when (region){
          CodeProvince.Aceh.code->CodeProvince.Aceh.nameProvince
          CodeProvince.Bali.code -> CodeProvince.Bali.nameProvince
          CodeProvince.KepBangkaBelitung.code -> CodeProvince.KepBangkaBelitung.nameProvince
          CodeProvince.Banten.code -> CodeProvince.Banten.nameProvince
          CodeProvince.Bengkulu.code -> CodeProvince.Bengkulu.nameProvince
          CodeProvince.JawaTengah.code -> CodeProvince.JawaTengah.nameProvince
          CodeProvince.KalimantanTengah.code -> CodeProvince.KalimantanTengah.nameProvince
          CodeProvince.SulawesiTengah.code -> CodeProvince.SulawesiTengah.nameProvince
          CodeProvince.JawaTimur.code -> CodeProvince.JawaTimur.nameProvince
          CodeProvince.KalimantanTimur.code -> CodeProvince.KalimantanTimur.nameProvince
          CodeProvince.NusaTenggaraTimur.code -> CodeProvince.NusaTenggaraTimur.nameProvince
          CodeProvince.Gorontalo.code -> CodeProvince.Gorontalo.nameProvince
          CodeProvince.DKIJakarta.code -> CodeProvince.DKIJakarta.nameProvince
          CodeProvince.Jambi.code -> CodeProvince.Jambi.nameProvince
          CodeProvince.Lampung.code -> CodeProvince.Lampung.nameProvince
          CodeProvince.Maluku.code -> CodeProvince.Maluku.nameProvince
          CodeProvince.KalimantanUtara.code -> CodeProvince.KalimantanUtara.nameProvince
          CodeProvince.MalukuUtara.code -> CodeProvince.MalukuUtara.nameProvince
          CodeProvince.SulawesiUtara.code -> CodeProvince.SulawesiUtara.nameProvince
          CodeProvince.SumateraUtara.code -> CodeProvince.SumateraUtara.nameProvince
          CodeProvince.Papua.code -> CodeProvince.Papua.nameProvince
          CodeProvince.Riau.code -> CodeProvince.Riau.nameProvince
          CodeProvince.KepulauanRiau.code -> CodeProvince.KepulauanRiau.nameProvince
          CodeProvince.SulawesiTenggara.code -> CodeProvince.SulawesiTenggara.nameProvince
          CodeProvince.KalimantanSelatan.code -> CodeProvince.KalimantanSelatan.nameProvince
          CodeProvince.SulawesiSelatan.code -> CodeProvince.SulawesiSelatan.nameProvince
          CodeProvince.SumateraSelatan.code -> CodeProvince.SumateraSelatan.nameProvince
          CodeProvince.DIYogyakarta.code -> CodeProvince.DIYogyakarta.nameProvince
          CodeProvince.JawaBarat.code -> CodeProvince.JawaBarat.nameProvince
          CodeProvince.KalimantanBarat.code -> CodeProvince.KalimantanBarat.nameProvince
          CodeProvince.NusaTenggaraBarat.code -> CodeProvince.NusaTenggaraBarat.nameProvince
          CodeProvince.PapuaBarat.code -> CodeProvince.PapuaBarat.nameProvince
          CodeProvince.SulawesiBarat.code -> CodeProvince.SulawesiBarat.nameProvince
          CodeProvince.SumateraBarat.code -> CodeProvince.SumateraBarat.nameProvince
          else -> CodeProvince.Unknown.nameProvince
      }
    }
}