function Map() {
    this.map = new ol.Map({
        target: 'map',
        view: new ol.View({
            center: [0, 0],
            zoom: 4
        })
    });
    this.tileLayer;
    this.vectorLayer;
    this.vectorSource;
    this.imageLayer;
    this.imageSource;
}
Map.prototype = {
    constructor: Map,
    initialize: function () {
        this.initTileLayer();
        this.initMousePositionControl();
        this.initVectorLayer();
        this.initImageLayer();
    },
    initTileLayer: function (options) {
        this.tileLayer = new ol.layer.Tile({
            source: new ol.source.XYZ({
                attributions: new ol.Attribution({
                    html: "<a href='http://www.jlrtls.com/'>&copy; 旌霖科技</a>"
                }),
                //url: 'http://www.google.cn/maps/vt?lyrs=y@802&gl=cn&x={x}&y={y}&z={z}'
                url:"http://www.google.cn/maps/vt?lyrs=m@189&gl=cn&x={x}&y={y}&z={z}"
            })
        });
        this.map.addLayer(this.tileLayer);
        var center = ol.proj.fromLonLat([113.2569444444, 23.1336111111]);
        var view = this.map.getView();
        view.setCenter(center);
    },
    initMousePositionControl: function () {
        var mousePositionControl = new ol.control.MousePosition({
            coordinateFormat: ol.coordinate.createStringXY(6),
            projection: 'EPSG:4326',
            className: 'custom-mouse-position',
            target: document.getElementById('mouse-position'),
            undefinedHTML: '&nbsp'
        });
        this.map.addControl(mousePositionControl);
    },
    initVectorLayer: function () {
        this.vectorSource = new ol.source.Vector({
            wrapX: false //不在地图上重复
        });
        this.vectorLayer = new ol.layer.Vector({
            source: this.vectorSource,
            style: new ol.style.Style({ //默认样式
                fill: new ol.style.Fill({
                    color: 'rgba(255, 204, 51, 0.5)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#ffcc33',
                    width: 0
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: '#ffcc33'
                    })
                })
            })
        });
        this.map.addLayer(this.vectorLayer);
    },
    initImageLayer: function (extent) {
        this.imageLayer = new ol.layer.Image({
            // source: this.imageSource
        })
        this.map.addLayer(this.imageLayer);
    },
    addCircle: function (lonlat, radius, colorName) {
        var circle = new ol.geom.Circle(ol.proj.transform(lonlat, 'EPSG:4326', 'EPSG:3857'), radius);
        var circleFeature = new ol.Feature(circle);
        circleFeature.setStyle(
            new ol.style.Style({
                fill: new ol.style.Fill({
                    color: '#0a3399CC'
                }),
				text: new ol.style.Text({
					text: '圆',
					scale: 1.3,
					fill: new ol.style.Fill({
					  color: '#000000'
					}),
					stroke: new ol.style.Stroke({
					  color: '#FFFF99',
					  width: 3.5
					})
				})
            })
        );
        this.vectorSource.addFeature(circleFeature);
    },
    addPolygon: function (values) {
        var polygon = new ol.geom.Polygon(values);
        polygon.applyTransform(ol.proj.getTransform('EPSG:4326', 'EPSG:3857'));
        var feature = new ol.Feature(polygon);
        feature.setStyle(
            new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 204, 51, 0.5)'
                }),
				text: new ol.style.Text({
					text: '多边形',
					scale: 1.3,
					fill: new ol.style.Fill({
					  color: '#000000'
					}),
					stroke: new ol.style.Stroke({
					  color: '#FFFF99',
					  width: 3.5
					})
				})
            })
        );
        this.vectorSource.addFeature(feature);
    },
    addIcon: function (lonlat, tip) {
        var point = new ol.geom.Point(ol.proj.transform(lonlat, 'EPSG:4326', 'EPSG:3857'));
        var pointFeature = new ol.Feature(point);
        pointFeature.setStyle(
            new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 0.5],
                    crossOrigin: 'anonymous',
                    src: 'https://openlayers.org/en/v4.6.5/examples/data/icon.png'
                }),
				text: new ol.style.Text({
					text: tip,
					scale: 1.3,
					fill: new ol.style.Fill({
					  color: '#000000'
					}),
					stroke: new ol.style.Stroke({
					  color: '#FFFF99',
					  width: 3.5
					})
				})
            })
        );
        this.vectorSource.addFeature(pointFeature);
    },
    addImage: function (extent, url) {
        var imageExtent = extent;//左下角右上角[113, 30.2, 115, 32.2]
        this.imageSource = new ol.source.ImageStatic({
            url: url,
            projection: 'EPSG:3857',
            imageExtent: ol.proj.transformExtent(imageExtent, 'EPSG:4326', 'EPSG:3857')
        })
        this.imageLayer.setSource(this.imageSource);
    }
}

var map = new Map();
map.initialize();

function addCircle(lonlat, radius, colorName) {
    map.addCircle(lonlat, radius, colorName);
}

function addPolygon(values) {
    map.addPolygon(values);
}

function addIcon(lonlat, tip) {
    map.addIcon(lonlat, tip);
}

function addImage(extent, url) {
    map.addImage(extent, url);
}

addCircle([113.2569444444, 23.1336111111], 400000, '#3399CC');
addPolygon([[[110, 39], [116, 39], [116, 33], [113, 30], [110, 33], [110, 39]]]);

addIcon([122, 30.2], '图标');
addImage([117, 30.2, 119, 32.2], '../static/staticres/Gis/1.png');