
var map;
var mapContainer;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var positions = [
    {
		name : '주안센터',
        content: '인천 남구 석바위로 68 필프라자 201호(주안역 지하8번출구)', 
    },
    {
		name : '부평센터',
        content: '인천 부평구 광장로 16 부평역사1층 이벤트광장 옆', 
    },
    {
		name : '부천센터',
        content: '경기 부천시 원미구 부천로 4 경동빌딩 301호(부천역 4번 출구)', 
    },
    {
		name : '상동센터',
        content: '경기 부천시 원미구 상동로 87 가나베스트타운205호(상동역3번출구)',
    }
];
for (var i = 0; i < positions.length; i ++) {
	geocoder.addressSearch(positions[i].content, function(result, status) {
		// 정상적으로 검색이 완료됐으면 
		 if (status === kakao.maps.services.Status.OK) {
	
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});
	
			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: positions[i].content 
			});
			infowindow.open(map, marker);
	
			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			if(i==0){
				map.setCenter(coords);
			}
		} 

	})

	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
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