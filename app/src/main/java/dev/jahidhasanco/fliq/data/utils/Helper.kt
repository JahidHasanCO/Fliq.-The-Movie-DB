package dev.jahidhasanco.fliq.data.utils

import java.text.SimpleDateFormat
import java.util.*

class Helper {

    companion object{

        public fun CompareDate(valid_until:String): Int{

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val strDate: Date = sdf.parse(valid_until)
            if (System.currentTimeMillis() > strDate.time) {
                return 1
            }
            return 2
        }
    }
}