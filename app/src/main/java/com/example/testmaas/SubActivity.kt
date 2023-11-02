package com.example.testmaas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kr.co.geosoft.indoor.parking.navi.ui.CustomIndoorView

class SubActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val customIndoorView = findViewById<CustomIndoorView>(R.id.custom_indoor_view)
        val valueString = intent.getStringExtra("jsonString")

        /**
         * 라이브러리 함수 호출 (startNavigation)
         * startNavigation 메소드는 라이브러리 진입 시 바로 호출
         * MAAS 앱에서 SDK 로 jsonString 값 전달
         * ParkingLotID, VehicleID, BaseURL, GateID(미사용) 전달
         */
        customIndoorView.startNavigation {
            if (valueString != null) {
                customIndoorView.jsonString = valueString
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            customIndoorView.sdkShutdown { }
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            customIndoorView.sdkPause { }
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            customIndoorView.sdkResume { }
        }

        /**
         * 라이브러리 함수 호출 (sdkShutdown)
         * sdkShutdown 메소드 호출시 sdk 종료
         * SDK에서 앱으로 운행 종료 상태를 체크 후 String 값 전달
         * 해당 메소드로 sdk 종료 시 checkNavigationState 값 3번
         */
//        customIndoorView.sdkShutdown { }
    }

    /**
     * 라이브러리 함수 호출 (showCancelAlertDialog)
     * 백 버튼 클릭 시 종료 dialog 출력
     * 해당 메소드로 sdk 종료 시 checkNavigationState 값 4번
     */
    override fun onBackPressed() {
        val customIndoorView = findViewById<CustomIndoorView>(R.id.custom_indoor_view)
        customIndoorView.showCancelAlertDialog()
    }
}