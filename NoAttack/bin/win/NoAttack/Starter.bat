@echo off
echo.---------------------
echo. Inicio del programa
echo.---------------------

if "%1"=="" (
java -jar bin\no-attack.jar
) else (
java -jar bin\no-attack.jar input-type=file input=%1 data-folder=%~dp0\data\ conf-folder=%~dp0\conf\
)

echo.
echo.---------------------
echo.  Fin del programa
echo.---------------------
pause > nul
