<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Cel</h1>
<p>
	Celem gry jest napisanie programu, który posteruje KodBotem tak, aby ten zebrał wszystkie znajdujące się na planszy baterie. Do napisania programu gracz musi posłużyć się zbiorem dostępnych instrukcji, które są dokładnie opisane
	<a href='<c:url value="/help"/>'>tutaj</a>
	. Im mniej komend zostanie użytych do napisania programu, tym więcej punktów zostanie przyznanych za ukończenie planszy. Na wynik ma również wpływ ilość pozostałej energii po ukończeniu poziomu.
</p>

<h1>Zasady</h1>
<p>Robot musi posiadać energię, aby mógł wykonywać ruchy. Kiedy poziom energii będzie niewystarczający robot nie będzie w stanie wykonać kolejnego polecenia. Ruch do przodu oraz obrót w lewo i prawo kosztują 5 jednostek energii. Do
	wykonania skoku potrzebna jest większa ilość energii i wynosi ona 10 jednostek. Wskaźnik energii na ekranie gry przedstawia następujący element graficzny:</p>
<div>
	<img src='<c:url value="/resources/images/content/rules/batteryFull.png" />' />
</div>
<p>Energię zdobywa się poprzez zbieranie tych samych baterii, których zebranie jest konieczne do ukończenia poziomu. Baterie w zależności od swojego typu dodają różne ilości energii.</p>
<div>
	<img src='<c:url value="/resources/images/content/rules/batteryLow.png" />' />
	- Dodaje 25 energii
</div>
<div>
	<img src='<c:url value="/resources/images/content/rules/batteryMedium.png" />' />
	- Dodaje 50 energii
</div>
<div>
	<img src='<c:url value="/resources/images/content/rules/batteryHigh.png" />' />
	- Dodaje 75 energii
</div>
<br/>
<div>
	<p>Gra posiada system odznak, które nagradzają gracza za osiągnięcia jakich dokonał podczas swojej przygody z grą KodBot. Gorąco zachęcamy do zmierzenia się z wszystkimi 20 dostępnymi poziomami oraz do zmierzenia się z osiągnięciami innych graczy.</p>
	<h2>Powodzenia! :)</h2>
</div>
