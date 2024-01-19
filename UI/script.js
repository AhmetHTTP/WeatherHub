function havaDurumuKontrol(type) {
    var sehirAdi = document.getElementById("sehirInput").value;
    var apiUrl;

    if (type === 'today') {
        apiUrl = "http://localhost:8080/search/" + sehirAdi;
    } else if (type === 'week') {
        apiUrl = "http://localhost:8080/forecast/" + sehirAdi;
    }
    console.log(apiUrl);
    
    

    // API gelir ve sonuçlar işlenir.
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            var selectedCityElement = document.getElementById("selectedCity");
            console.log(temp_max,temp_min, humidity);    
            selectedCityElement.innerHTML = sehirAdi;

            // Gerekli HTML elementlerine değerleri yazdırıyoruz.
            var tempElementi = document.getElementById("temp");
            tempElementi.innerHTML = data[0].todays_weather.today.temp;
            
            var humElementi = document.getElementById("humidity");
            humElementi.innerHTML = "%"+data[0].todays_weather.today.humidity;
            nemKontrol(data[0].todays_weather.today.humidity);

            var pressureElementi = document.getElementById("pressure");
            pressureElementi.innerHTML = data[0].todays_weather.today.pressure;

            var minTempElementi = document.getElementById("temp_min");
            minTempElementi.innerHTML = data[0].todays_weather.today.temp_min + "°C";
            var maxTempElementi = document.getElementById("temp_max");
            maxTempElementi.innerHTML = data[0].todays_weather.today.temp_max + "°C";

 
            var mainElementi = document.getElementById("durum");
            mainElementi.innerHTML = data[0].todays_weather.today.main;
            changeBackground(mainElementi.innerHTML);
            var descriptionElementi = document.getElementById("description");
            descriptionElementi.innerHTML = data[0].todays_weather.today.description;

            var radioApiUrl = "http://localhost:8080/radio-stations/recommendations/" + mainElementi.innerHTML;
           
            
            fetch(radioApiUrl)
            .then(response => response.json())
            .then(radioData => {
                console.log(radioData);
                var radioNameElementi = document.getElementById("name");
                radioNameElementi.innerHTML = radioData[0].name;

                var radioGenreElementi = document.getElementById("genre");
                radioGenreElementi.innerHTML = radioData[0].genre;
                
                var radioURLElementi = document.getElementById("url");
                radioURLElementi.innerHTML = '<img src="play.png" alt="Listen Icon" data-url="' + radioData[0].url + '">';
                

                radioURLElementi.style.cursor = "pointer";
                radioURLElementi.addEventListener("click", function() {

                    var url = radioURLElementi.querySelector("img").getAttribute("data-url");
                    if (url) {
                        window.open(url, "_blank");
                    }
                });
                
              
            })
            .catch(error => {
                console.log("Radio API Hatası: " + error);
            });
        })
        .catch(error => {
            console.log("Hata!!!!!!" + error);
        });

        function getDayOfWeek(dayIndex) {
            var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
            return days[dayIndex];
        }

        function getCurrentTime(hours, minutes) {
        
        var formattedHours = hours < 10 ? "0" + hours : hours;
        var formattedMinutes = minutes < 10 ? "0" + minutes : minutes;
    
        
        return formattedHours + ":" + formattedMinutes;
        }

        function havaSıcakligiKontrol(tempDegeri){
        var uvTextElementi = document.querySelector(".cards .card2 .content .uv-text");
            if (uvIndex < 0) {
                uvTextElementi.innerHTML = "Freezing";
            } else if (uvIndex >= 0 && uvIndex < 10) {
                uvTextElementi.innerHTML = "Cold";
            } else if (uvIndex >= 10 && uvIndex < 18) {
                uvTextElementi.innerHTML = "Warm";
            } else if (uvIndex >= 18 && uvIndex < 27) {
                uvTextElementi.innerHTML = "Hot";
            } else {
                uvTextElementi.innerHTML = "Extremely Hot";
            }
        }
        
        function nemKontrol(humidityDegeri){
            var humidityTextElementi = document.querySelector(".cards .card2 .content .humidity-status");
                    if (humidityDegeri < 0) {
                        humidityTextElementi.innerHTML = "Very Dry";
                    } else if (humidityDegeri >= 0 && humidityDegeri < 40) {
                        humidityTextElementi.innerHTML = "Dry";
                    } else if (humidityDegeri >= 40 && humidityDegeri < 65) {
                        humidityTextElementi.innerHTML = "Normal";
                    } else if (humidityDegeri >= 65 && humidityDegeri < 100) {
                        humidityTextElementi.innerHTML = "Very Humid";
                    } else {
                        humidityTextElementi.innerHTML = "NaN";
                    }
        }
        function changeBackground(weatherCondition) {
            var bodyElement = document.body;
            var imageUrl;
            console.log(weatherCondition);
            // Hava durumuna göre uygun arka plan resmini belirleme
            switch (weatherCondition) {
                case 'Clouds':
                    imageUrl = 'clouds.jpg';
                    break;
                case 'Rain':
                    imageUrl = 'rain.jpg';
                    break;
                case 'Clear':
                    imageUrl = 'clear.jpg';
                    break;
                case 'Snow':
                    imageUrl = 'snow.jpg';
                    break;
                default:
                    imageUrl = 'defaultt.jpg'; // Varsayılan
            }
        
            // Arka plan resmini ayarlama
            bodyElement.style.backgroundImage = 'url(' + imageUrl + ')';
        }     
}

