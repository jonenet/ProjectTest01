package com.example.dragger.dialogapp

import android.os.Parcel
import android.os.Parcelable

/**
 * Desc: TODO
 *
 *
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp
 * ProjectName: ProjectTest01
 * Date: 2019/10/16 14:22
 */
class Test private constructor(`in`: Parcel) : Parcelable {

    var name: String? = null
    var age: Int = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(age)
        dest.writeString(name)
    }

    init {
        name = `in`.readString()
        age = `in`.readInt()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Test> = object : Parcelable.Creator<Test> {

            override fun createFromParcel(source: Parcel): Test {
                return Test(source)
            }

            override fun newArray(size: Int): Array<Test?> {
                return arrayOfNulls(size)
            }
        }
    }


}
