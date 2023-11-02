package com.example.testmaas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.mapbox.mapboxsdk.Mapbox

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.geo_map_access_token))
        setContentView(R.layout.activity_main)

        val testButton = findViewById<Button>(R.id.button)
        val textVehicleId = findViewById<TextInputEditText>(R.id.tiet_vehicle_id)
        val textBuildingId = findViewById<TextInputEditText>(R.id.building_id)

        /**
         * 버튼 클릭시 지도 라이브러리 호출
         */
        testButton.setOnClickListener {
            val buildingId: String = textBuildingId.text.toString() // 입력받은 건물 ID
            val vehicleId: String = textVehicleId.text.toString() // 입력받은 차량 ID
            val jsonString: String = "{\n" +
                    "\"data\": {\n" +
                    "\"ParkingLotID\": \"$buildingId\",\n" +
                    "\"VehicleID\": \"$vehicleId\",\n" +
                    "\"BaseURL\": \"https://parkinglead.sejongsmartcity.kr:4443/service/parking-lot/\",\n" +
                    "\"GateID\": \"lprenter1\"\n" +
                    "}\n" +
                    "}"
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("jsonString", jsonString)
            startActivity(intent)
        }
    }
}