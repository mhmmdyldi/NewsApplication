package com.mhmmd.snappnews.data.model.api;

import com.google.gson.annotations.SerializedName;
import com.mhmmd.snappnews.data.model.db.SourceEntity;

import java.util.List;

public class SourceResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("sources")
    public List<SourceEntity> sources;
}
