package com.android.base.who.dataCenter.source.remote.netServices.converter;

/**
 * Created by huchunjie on 2018/12/20.
 */

import com.brightcns.dxlc.mvp.bean.BaseResp;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 全部转换为string，给callback反射
 */
public class StringResponseBodyConverter<T> implements Converter<ResponseBody, BaseResp<T>> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    StringResponseBodyConverter(Gson gson,TypeAdapter<T> adapter){
        this.adapter = adapter;
        this.gson = gson;
    }

    @Override
    public BaseResp<T> convert(ResponseBody value) throws IOException {
        try {
            BaseResp<String> objectBaseResp = new BaseResp<>();
            JsonReader jsonReader = gson.newJsonReader(value.charStream());
            jsonReader.beginObject();
            while(jsonReader.hasNext()){
                String tagName = jsonReader.nextName();
                if (tagName.equals("bizCode")) {
                    String bizCode = jsonReader.nextString();
                    objectBaseResp.setBizCode(bizCode);
                }else if (tagName.equals("bizMsg")) {
                    String bizMsg = jsonReader.nextString();
                    objectBaseResp.setBizMsg(bizMsg);
                }else if (tagName.equals("code")) {
                    String code = jsonReader.nextString();
                    objectBaseResp.setCode(code);
                }else if (tagName.equals("msg")) {
                    String msg = jsonReader.nextString();
                    objectBaseResp.setMsg(msg);
                }else if(tagName.equals("bizResp")){
                    String bizResp = jsonReader.nextString();
                    objectBaseResp.setBizResp(bizResp);
                }else {
                    jsonReader.skipValue();//跳过当前值
                }
            }
            jsonReader.endObject();
            return (BaseResp<T>) objectBaseResp;
        }finally {
            value.close();
        }
    }

}