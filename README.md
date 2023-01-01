# EvolutionGenerator
## Najpiękniejszy generator ewolucyjny, jaki widziała ludzkość.

*Dawno, dawno temu, dawniej niż narodziła się Maryla Rodowicz (tak, da się, też się zdziwiłyśmy!), powstały pierwsze istoty ziemskie. Wciąż wiemy o nich niewiele, jednak umiemy wyobrazić sobie jak wyglądał proces ich tworzenia. Ba! Umiemy to nawet zaimplementować!*

**EvolutionGenerator** to projekt stworzony na potrzeby przedmiotu "Programowanie Obiektowe" na kierunku Informatyka na Akademii Górniczo-Hutniczej. Autorkami programu są **Aleksandra Smela oraz Julia Smerdel**.

Program to sumulacja ewolucji, która pozwala zobaczyć proces rozwoju zwierząt w zależności od wybranych wariantów świata, umożliwia śledzenie ich statystyk oraz zapisywanie danych do plików CSV. Więcej szczegółów można znaleźć pod linkiem: https://github.com/apohllo/obiektowe-lab/tree/master/proj1 .



## Jak wczytywać dane do pliku?
Jak dobrze wiemy, wiele zdarzeń zależy od fazy księżyca, ułożenia gwiazd na nieboskłonie czy tego, którą nogą wstała dzisiaj Pani Basia, która jest sprzedawczynią w naszym ulubionym sklepie. 
Nasz program uwzględnia prawie wszystkie te możliwości, a bynajmniej niektóre z nich. No kilka. Ale takie ważniejsze.

**Dostępne warianty:**
- MAPA: GlobMap, HellMap;
- ZALESIANIE: ForestedEquators, ToxicCorpses;
- MUTACJE: LittleAdjustment, TotalRandom;
- ZACHOWANIE GENÓW: PredestinationBehavior, HijinksBehavior;

Aby zobaczyć stworzony przez nas cud technologiczny, należy stworzyć plik CSV (polecamy np.: program Excel), w którym umieścimy dane do naszej symulacji. **Kolejność wpisywania danych jest istotna.** Poniżej znajduje się przykładowy plik z konfiguracją:

[TestPO.csv](https://github.com/smelaa/EvolutionGenerator/files/10328324/TestPO.csv)

Kolejność wpisywania danych:
1) wariant mapy
2) wariant zalesiania
3) wariant mutacji
4) wariant zachowania genów
5) wysokość mapy
6) szerokość mapy
7) energia uzyskana przez zjedzenie trawy
8) minimalna energia potrzebna do stworzenia potomka
9) startowa energia zwierząt
10) dzienny koszt życia zwierząt
11) liczba zwierząt na start
12) liczba trawy rosnąca każdego dnia
13) czas odświeżania symulacji (wartość podawana w milisekundach)
14) długość genomu
15) true/false

*Ad 15)* jeśli chcemy, aby statystyki z każdego dnia symulacji zapisywane były w nowym pliku CSV, konieczne jest wpisanie w tym polu wartości *true*. Wtedy w argumencie 16) podajemy również ścieżkę do stworzonego już przez nas pliku. 

Gdy nie chcemy zapisywać statystyk do oddzielnego pliku, w argumencie 15) wpisujemy wartość *false* (nie wypełniamy już argumentu 16) ).


## Co, jak, z czym
Gdyby do kogoś nie przemówił rożowy, piękny kolor gui, spieszymy z pomocą i tłumaczymy, z czym to się je. Ale od początku.

Po wpisaniu ścieżki do pliku, wyświetla się najcudowniejsze okno dialogowe. W lewym górnym rogu znajduja się dwa przyciski: PLAY oraz PAUSE. Jak nazwa wskazuje, przycisk PAUSE odpowiada za zatrzymanie symulacji, a przycisk PLAY za jej wznowienie. 
Pod spodem zaś umieszczone są statystyki symulacji oraz statystyki aktualnie śledzonego zwierzaczka (zwierzaczki są okrąglutkie - ale to samo futerko! Nasze zwierzaczki są fit, nie są grubaskami, zapewniamy!). Aby zacząć śledzić zwierzaka, wystarczy kliknąć na niego lewym przyciskiek myszy - wtedy zwierzako-kulka zmieni kolor na zielony.
Zwierzaki z najpopularniejszym genomem wyróżnione są za pomocą żółtego obramowania.
Po prawej stronie, nie do przeoczenia ze względu na swą wyjątkowość, estetykę i inne arybuty, widoczna jest mapa. To właśnie na niej można śledzić symulację.
Pod mapą znajduje się przycisk STOP SIMULATION, który zatrzymuje symulację.



## Filmiki:
https://www.youtube.com/watch?v=dQw4w9WgXcQ



