package ekzeget.ru.ekzeget.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "bible")
public class Bible {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId ;

    @ColumnInfo(name = "st_no")
    private String mStNo;

    @ColumnInfo(name = "kn")
    private String mKn;

    @ColumnInfo(name = "st_text")
    private String mStText;

    @ColumnInfo(name = "parallel")
    private String mParallel;

    @ColumnInfo(name = "new_text")
    private String mNewText;

    @ColumnInfo(name = "kassian")
    private String mKassian;

    @ColumnInfo(name = "podstr")
    private String mPodstr;

    @ColumnInfo(name = "csya")
    private String mCsya;

    @ColumnInfo(name = "averincev")
    private String mAverincev;

    @ColumnInfo(name = "ungerov")
    private String mUngerov;

    @ColumnInfo(name = "grek")
    private String mGrek;

    @ColumnInfo(name = "csya_old")
    private String mCsyaOld;

    @ColumnInfo(name = "sovr_rbo")
    private String mSovrRbo;

    @ColumnInfo(name = "latin")
    private String mLatin;

    @ColumnInfo(name = "ukr")
    private String mUkr;

    @ColumnInfo(name = "nkjv")
    private String mNkjv;

    public Bible() {

    }

//    @Ignore
//    public BibleModel(String userName) {
//        this.mStText = userName;
//    }
//
//    public BibleModel(int id, String userName) {
//        this.mId = id;
//        this.mStText = userName;
//    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getStNo() {
        return mStNo;
    }

    public void setStNo(String mStNo) {
        this.mStNo = mStNo;
    }

    public String getKn() {
        return mKn;
    }

    public void setKn(String mKn) {
        this.mKn = mKn;
    }

    public String getStText() {
        return mStText;
    }

    public void setStText(String mStText) {
        this.mStText = mStText;
    }

    public String getParallel() {
        return mParallel;
    }

    public void setParallel(String mParallel) {
        this.mParallel = mParallel;
    }

    public String getNewText() {
        return mNewText;
    }

    public void setNewText(String mNewText) {
        this.mNewText = mNewText;
    }

    public String getKassian() {
        return mKassian;
    }

    public void setKassian(String mKassian) {
        this.mKassian = mKassian;
    }

    public String getPodstr() {
        return mPodstr;
    }

    public void setPodstr(String mPodstr) {
        this.mPodstr = mPodstr;
    }

    public String getCsya() {
        return mCsya;
    }

    public void setCsya(String mCsya) {
        this.mCsya = mCsya;
    }

    public String getAverincev() {
        return mAverincev;
    }

    public void setAverincev(String mAverincev) {
        this.mAverincev = mAverincev;
    }

    public String getUngerov() {
        return mUngerov;
    }

    public void setUngerov(String mUngerov) {
        this.mUngerov = mUngerov;
    }

    public String getGrek() {
        return mGrek;
    }

    public void setGrek(String mGrek) {
        this.mGrek = mGrek;
    }

    public String getCsyaOld() {
        return mCsyaOld;
    }

    public void setCsyaOld(String mCsyaOld) {
        this.mCsyaOld = mCsyaOld;
    }

    public String getSovrRbo() {
        return mSovrRbo;
    }

    public void setSovrRbo(String mSovrRbo) {
        this.mSovrRbo = mSovrRbo;
    }

    public String getLatin() {
        return mLatin;
    }

    public void setLatin(String mLatin) {
        this.mLatin = mLatin;
    }

    public String getUkr() {
        return mUkr;
    }

    public void setUkr(String mUkr) {
        this.mUkr = mUkr;
    }

    public String getNkjv() {
        return mNkjv;
    }

    public void setNkjv(String mNkjv) {
        this.mNkjv = mNkjv;
    }
}
