package com.example.finalproject.api

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@SerializedName("statusCode")
	val statusCode: Int? = null,

	@SerializedName("result")
	val result: Result? = null

)

data class Result(

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("objects")
	val objects: Objects? = null,

	@SerializedName("arcs")
	val arcs: List<Any?>? = null,

	@SerializedName("bbox")
	val bbox: List<Any?>? = null

)

data class Objects(

	@SerializedName("output")
	val output: Output? = null

)

data class Output(

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("geometries")
	val geometries: List<GeometriesItem>? = null

)

data class GeometriesItem(

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("properties")
	val properties: Properties? = null,

	@SerializedName("coordinates")
	val coordinates: List<Double>? = null

)

data class Properties(

	@SerializedName("pkey")
	val pkey: String? = null,

	@SerializedName("created_at")
	val createdAt: String? = null,

	@SerializedName("source")
	val source: String? = null,

	@SerializedName("status")
	val status: String? = null,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("image_url")
	val imageUrl: Any? = null,

	@SerializedName("disaster_type")
	val disasterType: String? = null,

	@SerializedName("report_data")
	val reportData: ReportData? = null,

	@SerializedName("tags")
	val tags: Tags? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("text")
	val text: String? = null,

	@SerializedName("partner_code")
	val partnerCode: Any? = null,

	@SerializedName("partner_icon")
	val partnerIcon: Any? = null,

	)

data class ReportData(

	@SerializedName("report_type")
	val reportType: String? = null,

	@SerializedName("flood_depth")
	val floodDepth: Int? = null,

	@SerializedName("structureFailure")
	val structureFailure: Int? = null,

	@SerializedName("impact")
	val impact: Int? = null,

	@SerializedName("personLocation")
	val personLocation: PersonLocation? = null,

	@SerializedName("fireLocation")
	val fireLocation: FireLocation? = null,

	@SerializedName("fireRadius")
	val fireRadius: FireRadius? = null,

	@SerializedName("fireDistance")
	val fireDistance: Any? = null,

	@SerializedName("airQuality")
	val airQuality: Int? = null,

	@SerializedName("visibility")
	val visibility: Int? = null,

	@SerializedName("volcanicSigns")
	val volcanicSigns: List<Int?>? = null,

	@SerializedName("evacuationArea")
	val evacuationArea: Boolean? = null,

	@SerializedName("evacuationNumber")
	val evacuationNumber: Int? = null,

	@SerializedName("condition")
	val condition: Int? = null,

	@SerializedName("accessabilityFailure")
	val accessabilityFailure: Int? = null

)

data class Tags(

	@SerializedName("district_id")
	val districtId: Any? = null,

	@SerializedName("region_code")
	val regionCode: String? = null,

	@SerializedName("local_area_id")
	val localAreaId: Any? = null,

	@SerializedName("instance_region_code")
	val instanceRegionCode: String? = null,

	)

data class FireRadius(

	@SerializedName("lng")
	val lng: Any? = null,

	@SerializedName("lat")
	val lat: Any? = null

)

data class PersonLocation(

	@SerializedName("lng")
	val lng: Any? = null,

	@SerializedName("lat")
	val lat: Any? = null

)

data class FireLocation(

	@SerializedName("lng")
	val lng: Any? = null,

	@SerializedName("lat")
	val lat: Any? = null
)
