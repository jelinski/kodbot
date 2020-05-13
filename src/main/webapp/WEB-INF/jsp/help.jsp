<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/help" var="helpUrl" />
<h1>Instrukcje</h1>

<p>Do napisania programu w grze KodBot niezbędna jest znajomość instrukcji (zwanych również poleceniami). Jest ich tylko kilka i zostały one dokładnie wyjaśnione w dalszej części strony. Instrukcje dzielą się na pojedyncze i blokowe.</p>
<p>
	<strong>Instrukcje pojedyncze</strong>
	to takie, które służą do wykonania konkretnych prostych operacji jak np. poruszenie robotem, czy przypisanie wartości do zmiennej.
</p>
<p>
	Instrukcje pojedyncze można łączyć w większe i bardziej skomplikowane elementy za pomocą
	<strong>instrukcji blokowych</strong>
	. Do określenia zawartości ("ciała") instrukcji blokowej używa się nawiasów klamrowych: "{" oraz "}". Instrukcje blokowe mogą być zastosowane do często powtarzających się zadań np. możemy stworzyć definicję funkcji, która porusza robota o
	3 pola do przodu. Jeśli na planszy często poruszamy robotem właśnie o 3 pola do przodu to pozwoli to na napisanie programu przy użyciu mniejszej ilości instrukcji, przez co będzie on krótszy, bardziej czytelny i lepszej jakości. Pamiętaj,
	że ilość użytych instrukcji w programie przekłada się na wynik. Im mniej użytych instrukckji, tym lepiej.
</p>

<h4>Na końcu strony zostały umieszczone przykłady obrazujące zastosowanie poleceń</h4>

<h3>W grze KodBot dostępne są następujące instrukcje:</h3>
<ul>
	<li>
		Instrukcje pojedyncze
		<ul>
			<li>
				<a class="cm-keyword" href="${helpUrl}#move">move</a>
			</li>
			<li>
				<a class="cm-keyword" href="${helpUrl}#jump">jump</a>
			</li>
			<li>
				<a class="cm-keyword" href="${helpUrl}#left">left</a>
			</li>
			<li>
				<a class="cm-keyword" href="${helpUrl}#right">right</a>
			</li>
			<li>
				<a class="cm-variable" href="${helpUrl}#variable">zmienne</a>
			</li>
			<li>
				<a class="cm-function" href="${helpUrl}#function-call">wywołania funkcji</a>
			</li>
			<li>
				<a style="color: white" href="${helpUrl}#operators">operatory</a>
				<ul>
					<li>przypisanie</li>
					<li>przypisanie z dodawaniem</li>
					<li>przypisanie z odejmowaniem</li>
					<li>inkrementacja</li>
					<li>dekrementacja</li>
				</ul>
			</li>
		</ul>
	</li>
	<li>
		Instrukcje blokowe
		<ul>
			<li>
				<a class="cm-main" href="${helpUrl}#main">main</a>
			</li>
			<li>
				<a class="cm-keyword" href="${helpUrl}#repeat">repeat</a>
			</li>
			<li>
				<a class="cm-function" href="${helpUrl}#functions">definicje funkcji</a>
			</li>
		</ul>
	</li>
</ul>


<h2>Instrukcje pojedyncze</h2>
<p>Pamiętaj, aby na końcu polecenia postawić znak średnika ";"</p>


<h3 class="cm-keyword" id="move">move</h3>
<p>Polecenie to służy do poruszania robotem do przodu. Koszt ruchu do przodu wynosi 5 jednostek energii.</p>
<img alt="Move forward" src='<c:url value="/resources/images/content/help/move.png" />' />

<h3 class="cm-keyword" id="jump">jump</h3>
<p>Polecenie to służy do wskakiwania oraz zeskakiwania z bloczków. Możliwe jest wskakiwanie i zeskakiwanie tylko na elementy, które znajdują się jeden poziom niżej lub wyżej. Koszt tego ruchu to 10 jednostek energii.</p>
<div>
	<img alt="Jump up" src='<c:url value="/resources/images/content/help/jump1.png" />' />
	<img alt="Jump down" src='<c:url value="/resources/images/content/help/jump2.png" />' />
</div>

Bloczki na które można wskakiwać to:
<img alt="Standable blocks" src='<c:url value="/resources/images/content/help/boxHeavy.png" />' />
Natomiast nie można wkakiwać na:
<img alt="Unstandable blocks" src='<c:url value="/resources/images/content/help/boxSpiked.png" />' />


<h3 class="cm-keyword" id="left">left</h3>
<p>Instrukcja ta służy do obrócenia robota w lewą stronę o 90&deg;. Koszt tej operacji wynosi 5 jednostek energii.</p>
<img alt="Turn left" src='<c:url value="/resources/images/content/help/left.png" />' />

<h3 class="cm-keyword" id="right">right</h3>
<p>Instrukcja ta służy do obrócenia robota w prawą stronę o 90&deg;. Koszt tej operacji wynosi 5 jednostek energii.</p>
<img alt="Turn right" src='<c:url value="/resources/images/content/help/right.png" />' />

<h3 class="cm-variable" id="variable">zmienne</h3>
<p>Zmienne służą do przechowywania wartości liczbowej. W miejscu odwołania się do zmiennej tak naprawdę jest wstawiana jej aktualna wartość. Zmienne mają zakres globalny, co oznacza, że zmienna użyta w jednym miejscu programu może być
	wykorzystana z powodzeniem w innych jego częściach. Domyślnie jeśli nie przypiszemy do zmiennej żadnej wartości mają one przypisaną wartość 0. Maksymalna wartość jaką można przypisać do zmiennej to 20. Wartość ta jest w pełni wystarczająca
	aby ukończyć wszystkie plansze. Dzięki zmiennym możliwe jest między innymi tworzenie pętli, które wykonują się zmienną ilość razy. Patrz przykład na końcu strony.</p>
<p>Zmienne mogą mieć dowolną nazwę pod warunkiem, że zaczynają się od litery i nie zawierają znaków specjalnych za wyjątkiem podkreślenia "_".</p>
<p>Poprawne nazwy zmiennych to np.:</p>
<ul>
	<li>kotek13</li>
	<li>x</li>
	<li>to_jest_moja_zmienna</li>
</ul>

<p>Uwaga! Częstym błędem są literówki w nazwach zmiennych. Zawsze sprawdzaj poprawność swoich zmiennych podczas ich używania. Jeśli przypiszemy zmiennej "kotek" wartość 5, a następnie odwołamy się do zmiennej "ktoek" to uzyskamy wartość
	0, ponieważ tej zmiennej nie przypisaliśmy wcześniej żadnej wartości.</p>

<h3 class="cm-function" id="function-call">wywołania funkcji</h3>
<p>Żeby wywołać uprzednio zdefiniowaną funkcję wystarczy napisać jej nazwę i zakończyć ją znakiem średnika ";". Spowoduje to wykonanie instrukcji zdefiniowanych wewnątrz funkcji. Należy uważać na literówki, ponieważ możemy próbować
	wywołać funkcję, której nie zdefiniowaliśmy.</p>
<p>Dla ułatwienia po wpisaniu nazwy instniejącej zdefiniowanej funkcji jej nazwa zostanie zamieniona na niebieską.</p>
<p>Poprawne nazwy dla funkcji są takie same jak dla zmiennych i mogą składać się z liter, cyfr oraz znaku "_". Wymogiem jest jednak aby nazwa funkcji zaczynala się od litery.</p>
<p>Poprawne nazwy to:</p>
<ul>
	<li>funkcja1</li>
	<li>idz_5_przod</li>
	<li>moja_funkcja</li>
</ul>


<h3 id="operators">operatory</h3>
<h4>przypisanie</h4>
<p>Operator przypisania "=" służy do przypisania wartości do zmiennej. Przykład "x = 5"</p>

<h4>przypisanie z dodawaniem</h4>
<p>Operator przypisania z dodawaniem jest bardzo podobny do operatora przypisania. Różni się on od niego tym, że po prawej stronie znaku "=" muszą wystąpić dwa operandy (zmienne/liczby) odzielone znakiem "+".</p>
<p>Przykład: "x = y + 4"</p>


<h4>przypisanie z odejmowaniem</h4>
<p>Operator przypisania z odejmowaniem jest bardzo podobny do operatora przypisania. Różni się on od niego tym, że po prawej stronie znaku "=" muszą wystąpić dwa operandy (zmienne/liczby) odzielone znakiem "-".</p>
<p>Przykład: "x = y - 4"</p>


<h4>inkrementacja</h4>
<p>Operacja inkrementacji służy do zwiększenia wartości zmiennej o 1. Jest to skrócony zapis dla operatora przypisania z dodawaniem, gdzie pierwszym prawym operandem jest ta sama zmienna, która występuje po lewej stronie znaku "=".</p>
<p>Instrukcja "x++" jest równoważna instrukcji: "x=x+1"</p>


<h4>dekrementacja</h4>
<p>Operacja dekrementacji służy do zmniejszania wartości zmiennej o 1. Jest to skrócony zapis dla operatora przypisania z odejmowaniem, gdzie pierwszym prawym operandem jest ta sama zmienna, która występuje po lewej stronie znaku "=".</p>
<p>Instrukcja "x--" jest równoważna instrukcji: "x=x-1"</p>


<h2>Instrukcje blokowe</h2>
<p>Pamiętaj, aby zawartość instrukcji blokowej umieścić pomiędzy znakami "{" oraz "}".</p>

<h3 class="cm-main" id="main">main</h3>
Jest to podstawowa instrukcja blokowa wymagana w każdym programie. Określa ona miejsce w programie, od którego będzie rozpoczęte wykonywanie instrukcji.

<h3 class="cm-keyword" id="repeat">repeat</h3>
Instrukcja ta służy do tworzenia pętli. Instrukcje, które znajdują się wewnątrz bloku repeat zostaną wykonane dokładnie taką ilość razy jaka jest podana pomiędzy nawiasami. Pętle pozwalają znacznie zmniejszyć ilość używanych instrukcji
poprzez wyeliminowanie powtarzających się instrukcji. Pętle można zagnieżdżać.


<h3 class="cm-function" id="functions">definicje funkcji</h3>
Funkcje służą do grupowania fragmentów kodu, który jest często wykorzystywany. Pozwalają one zminimalizować ilość używanych poleceń. Często jednak lepsze rezultaty jesteśmy w stanie uzyskać za pomocą pętli. Funkcje świetnie nadają się dla
bardziej skomplikowanych zadań. Z poziomu jednej funkcji możemy wywoływać inne funkcje. Funkcje możemy definiować wyłącznie na najwyższym poziomie programu. Nie możemy definicji funkcji umieścić w bloku main, czy też w innej funkcji.
<div>
	<img alt="Separate function definition" src='<c:url value="/resources/images/content/help/function-def1.png" />' />
	<img alt="Nested function definition" src='<c:url value="/resources/images/content/help/function-def2.png" />' />
</div>


<h1>Przykłady</h1>
<h3>Poruszenie robota o 5 pól do przodu</h3>
<div>
	<img alt="Move five fields forward example" src='<c:url value="/resources/images/content/help/example1a.png" />' />
	<img alt="Move five fields forward example using loop" src='<c:url value="/resources/images/content/help/example1b.png" />' />
</div>
<p>Po prawej bardziej optymalne rozwiązanie wykorzystujące pętle</p>

<h3>Poruszanie się po kwadracie:</h3>
<div>
	<img alt="Square path example" src='<c:url value="/resources/images/content/help/example3a.png" />' />
</div>
<div>
	<img alt="Square path solution" src='<c:url value="/resources/images/content/help/example3b.png" />' />
</div>
<p>Ponieważ kwadrat ma długość 4 pól, to aby dojść do następnej krawędzi KodBot musi poruszyć się o 3 pola do przodu. Ruch ten realizuje pętla wewnętrzna. Gdy robot znajdzie się na krawędzi musi zmienić kierunek ruchu, dlatego w linii
	10 występuje polecenie left. Taką sekwencję ruchów należy wykonać 4 razy, ponieważ tyle boków ma kwadrat. Z tego powodu należy użyc pętli zewnętrznej z 4 powtórzeniami.</p>
<h3>Pętla o zmiennej ilości wykonań</h3>
<img alt="Nested loop with variable iteration count" src='<c:url value="/resources/images/content/help/example2.png" />' />
<p>Powyższy przykład spowoduje, że robot poruszy się dokładnie 12 razy do przodu (5 + 4 + 3).</p>
<p>Przeanalizujmy ten przykład: W linijce 3 przypisujemy zmiennej x wartość 5. Następnie definiujemy pętlę, która wykona się 3 razy. W tej pętli definiujemy kolejną pętlę, która powoduje poruszenie robota do przodu o x pól. Gdy robot
	poruszy się o x pól następuje zmniejszenie wartości x o 1. Zatem w pierwszym przebiegu robot pójdzie 5 pól do przodu, następnie w drugim przebiegu 4 pola, a w trzecim przebiegu 3 pola.</p>
