//Initializing the map
function setMap() {

	console.log('Loading map');

	var map = L.map('map').setView([ 59.8098366, 10.2678465 ], 15);
	// Set view takes two parameters;
	// 1. The coordinates for the center of the map
	// 2. The zoom level. ZoomLevel is from 0 -> 22, where 22 is zoomed in an 0
	// is zoomed out

	// Adding the base map. Base map decides how the background map looks like
	var basemapUrl = 'https://api.mapbox.com/styles/v1/mapbox/streets-v10/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWF0aGlsZG8iLCJhIjoiY2lrdHZvMHdsMDAxMHdvbTR0MWZkY3FtaCJ9.u4bFYLBtEGNv4Qaa8Uaqzw';
	L.tileLayer(basemapUrl).addTo(map);

	// Adding geoJSON layer to the map:
	L.geoJSON(lierIL, {
		onEachFeature : addPopUp,
		style : myStyle,
		//pointToLayer: geoMarkerOptions,
	}).addTo(map);


}

	/*var geojsonMarkerOptions = {
			"fillColor" : "#2B2D4",
			"color ": "#000",
			"weight" : 1,
			"opacity" : 1,
			"fillOpacity" : 0.8
		};*/

var myStyle = {
	"color" : "#2B2D4", // #ff7800
	"weight" : 5,
	"opacity" : 0.65
};

function addPopUp(feature, layer) {
	if (feature.properties && feature.properties.name) {
		layer.bindPopup(feature.properties.name);
	}
}

window.onload = setMap;
