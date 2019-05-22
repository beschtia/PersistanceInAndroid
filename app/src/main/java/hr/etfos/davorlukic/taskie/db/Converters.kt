package hr.etfos.davorlukic.taskie.db

import androidx.room.TypeConverter
import hr.etfos.davorlukic.taskie.model.Priority

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromPriority(value: Priority): Int {
            return  when (value){
                Priority.LOW -> 1
                Priority.MEDIUM -> 2
                else -> 3
            }
        }

        @TypeConverter
        @JvmStatic
        fun toPriority(value: Int): Priority {
            return when (value){
                1 -> Priority.LOW
                2 -> Priority.MEDIUM
                else -> Priority.HIGH
            }
        }
    }
}