@echo off
echo ===============================
echo Building Spring Boot project...
echo ===============================

REM Verifică dacă Maven este instalat
mvn -v >nul 2>&1
IF ERRORLEVEL 1 (
    echo [ERROR] Maven nu este instalat sau nu este în PATH.
    echo Te rog să configurezi variabila de mediu pentru Maven.
    pause
    exit /b 1
)

REM Rulează build-ul
mvn clean package

IF ERRORLEVEL 1 (
    echo [ERROR] Build-ul a eșuat.
    pause
    exit /b 1
)

echo ===============================
echo Build finalizat cu succes!
echo Fișierul JAR se află în folderul /target
echo ===============================
pause