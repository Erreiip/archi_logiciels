@echo off

call javac -encoding utf8 "@compile.list"

echo Lancement du programme...
call java -cp ./bin server.src.Main

echo Fin de l'execution.
goto :eof