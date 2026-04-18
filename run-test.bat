@echo off
cd /d "%~dp0"

set TESTS[1]=Test_LayoutBuilder
set TESTS[2]=Test_LayoutBuilder2
set TESTS[3]=Test_ManualGeometryProviderBasic
set TESTS[4]=Test_OverlapSolverManual
set TESTS[5]=Test_OverlapSolverRandom
set TESTS[6]=Test_OverlapSolverRandomBig
set TESTS[7]=Test_OverlapSolverRandomProvider
set TESTS[8]=Test_RectCollision
set TESTS[9]=Test_RectTouching
set TESTS[10]=Test_Restrictions

:menu
echo.
echo Available troidgen tests:
echo   1) Test_LayoutBuilder
echo   2) Test_LayoutBuilder2
echo   3) Test_ManualGeometryProviderBasic
echo   4) Test_OverlapSolverManual
echo   5) Test_OverlapSolverRandom
echo   6) Test_OverlapSolverRandomBig
echo   7) Test_OverlapSolverRandomProvider
echo   8) Test_RectCollision
echo   9) Test_RectTouching
echo  10) Test_Restrictions
echo.
set /p CHOICE="Select a test (1-10): "

if "%CHOICE%"=="1"  call :run Test_LayoutBuilder          & goto :eof
if "%CHOICE%"=="2"  call :run Test_LayoutBuilder2         & goto :eof
if "%CHOICE%"=="3"  call :run Test_ManualGeometryProviderBasic & goto :eof
if "%CHOICE%"=="4"  call :run Test_OverlapSolverManual    & goto :eof
if "%CHOICE%"=="5"  call :run Test_OverlapSolverRandom    & goto :eof
if "%CHOICE%"=="6"  call :run Test_OverlapSolverRandomBig & goto :eof
if "%CHOICE%"=="7"  call :run Test_OverlapSolverRandomProvider & goto :eof
if "%CHOICE%"=="8"  call :run Test_RectCollision          & goto :eof
if "%CHOICE%"=="9"  call :run Test_RectTouching           & goto :eof
if "%CHOICE%"=="10" call :run Test_Restrictions           & goto :eof

echo Invalid selection: %CHOICE%
goto menu

:run
echo.
echo Running %1 ...
call gradlew.bat "run_%1"
goto :eof
