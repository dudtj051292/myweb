

var map;
var mapContainer;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var houseNames      = document.getElementsByName("st_housename");
var houseAddreses   = document.getElementsByName("st_address"  );
var houseTelephones = document.getElementsByName("st_telephone");

var positions = new Array();

for(var i =0; i<houseAddreses.length; i++){
	positions[i] =
	{
		names : houseNames[i].innerHTML,
		content : houseAddreses[i].innerHTML,
		telephone : houseTelephones[i].innerHTML
	}
}
var locations = new Array;
for (var i = 0; i < positions.length; i ++) {
	console.log(" address Search 전 :"+ positions[i].content);

	geocoder.addressSearch(positions[i].content, function(result, status) {
		// 정상적으로 검색이 완료됐으면 
		 if (status === kakao.maps.services.Status.OK) {
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);		
			console.log("coords :"+ result[0]);
			locations.push(coords);
		} else{
			locations.push("");
		}
	})
}

positions.push(locations);
//전체 위치까지 받앗음.. 이제 이 전체 위치에 대해 인포윈도우 생성과 포커싱 해줄것..
console.log(positions);
for (var i = 0; i < positions.length-1; i ++) {
	console.log("i : " + positions[i])
	if(i==0){

		mapOption ={
			center : new kakao.maps.LatLng(positions[positions.length-1][0]["La"], positions[positions.length-1][0]["Ma"])
		}
	}

}



// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}