@echo off
if not exist "lib" mkdir lib
if not exist "bin" mkdir bin

echo Compiling...
javac -d bin src/com/library/model/*.java src/com/library/util/*.java src/com/library/dao/*.java src/com/library/service/*.java src/com/library/ui/*.java src/com/library/main/*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo Compilation successful!
echo.
echo NOTE: You need 'sqlite-jdbc' jar in the 'lib' folder to run the database.
echo Example file: sqlite-jdbc-3.42.0.0.jar
echo.

echo Running...
java -cp "bin;lib/*" com.library.main.Main
pause
