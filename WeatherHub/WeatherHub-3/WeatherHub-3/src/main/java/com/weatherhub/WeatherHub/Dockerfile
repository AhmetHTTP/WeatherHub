# Base image
FROM maven:3.8.4-openjdk-8-slim

# Çalışma dizini oluşturun
WORKDIR /app

# Uygulama kaynak kodunu Docker konteynerine kopyalayın
COPY . /app

# Bağımlılıkları çözün ve uygulamayı derleyin
RUN mvn clean package

# Uygulamayı başlatın
CMD ["java", "-jar", "/Users/murathankarasu/Downloads/WeatherHub-1/WeatherHub/WeatherHub-3/WeatherHub-3/target/classes/com/weatherhub"]

