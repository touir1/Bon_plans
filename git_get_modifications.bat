@echo off
call git pull
if ERRORLEVEL 1 goto error

COLOR 0A
pause
exit
:error
COLOR 0C
pause