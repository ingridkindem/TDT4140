//Initializing the map
function setMap() {

	console.log('Loading map');
	
	// Set view takes two parameters;
	// 1. The coordinates for the center of the map
	// 2. The zoom level. ZoomLevel is from 0 -> 22, 
	//where 22 is zoomed in an 0 is zoomed out
	var map = L.map('map').setView([ 59.8098366, 10.2678465 ], 15);

	// Adding the base map. Base map decides how the background map looks like
	var basemapUrl = 'https://api.mapbox.com/styles/v1/mapbox/streets-v10/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWF0aGlsZG8iLCJhIjoiY2lrdHZvMHdsMDAxMHdvbTR0MWZkY3FtaCJ9.u4bFYLBtEGNv4Qaa8Uaqzw';
	L.tileLayer(basemapUrl).addTo(map);

	//Customizing marker, so it fits to our graphical profile
	var myIcon = L.Icon.extend({iconUrl : 'pin.png'});

	// Adding geoJSON layer to the map:
	L.geoJSON(lierIL, {
		
		//Adds the popUp-function to each feature
		onEachFeature : addPopUp,

		//latlng is short for latitude and longitude in degrees, giving the coordinates
		//pointToLayer makes it possible to add customized marker
		pointToLayer : function(feature, latLng) {
			return new L.Marker(latLng, {
				icon : new myIcon({
					iconUrl : 'pin.png',
					iconSize : [ 36, 53 ],
					iconAnchor : [ 18, 53 ], //where tip of marker is placed, relative to top left corner
					popupAnchor : [ 1, -55 ]
				//where popUpBox is placed, relative to anchor	
				})
			})
		}
	}).addTo(map);

	//Gives all the features popUp, containing the name defined in location.js
	function addPopUp(feature, layer) {
		if (feature.properties && feature.properties.name) {
			layer.bindPopup(feature.properties.name +"<br>"+ feature.properties.addresse +"<br>"+ feature.properties.poststed);
		}
	}
}

window.onload = setMap;
