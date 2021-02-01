package com.suwonccc.csmproject.recyclerview
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class Mentee(val name: String, val thumb: String, val age: Int, val man: Boolean, val tag1: String, val tag2: String, val tag3: String, val tag4: String)

class Mentor(val name: String, val thumb: String, val age: Int, val man: Boolean, val campus: String, val dept: String, val tag1: String, val tag2: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(thumb)
        parcel.writeInt(age)
        parcel.writeByte(if (man) 1 else 0)
        parcel.writeString(campus)
        parcel.writeString(dept)
        parcel.writeString(tag1)
        parcel.writeString(tag2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mentor> {
        override fun createFromParcel(parcel: Parcel): Mentor {
            return Mentor(parcel)
        }

        override fun newArray(size: Int): Array<Mentor?> {
            return arrayOfNulls(size)
        }
    }
}

fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}