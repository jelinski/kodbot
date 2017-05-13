package pl.yeloon.magisterium.resolver.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.yeloon.magisterium.resolver.evaluator.command.AssignCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.AssignWithAdditionCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.AssignWithSubtractionCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.Command;
import pl.yeloon.magisterium.resolver.evaluator.command.DecrementCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.FunctionBlock;
import pl.yeloon.magisterium.resolver.evaluator.command.FunctionCallCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.IncrementCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.JumpCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.MainBlock;
import pl.yeloon.magisterium.resolver.evaluator.command.MoveCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.RepeatBlock;
import pl.yeloon.magisterium.resolver.evaluator.command.TurnLeftCommand;
import pl.yeloon.magisterium.resolver.evaluator.command.TurnRightCommand;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;

public class JsonParser extends Parser {

	private com.fasterxml.jackson.core.JsonParser jp;
	private List<Command> current;
	private List<Command> commands;
	private int commandCounter=0;

	private void process() throws JsonParseException, IOException, ParserException {

		if (jp.nextToken() == JsonToken.START_ARRAY) {

			while (jp.nextToken() != JsonToken.END_ARRAY && jp.getCurrentToken() != null) {

				if (jp.getCurrentToken() == JsonToken.START_OBJECT) {

					if (jp.nextToken() == JsonToken.FIELD_NAME) {

						String fieldName = jp.getCurrentName();

						if (fieldName.equals("command")) {

							if (jp.nextToken() == JsonToken.VALUE_STRING) {

								String fieldValue = jp.getText();

								if ("move".equals(fieldValue)) {
									processMove();
									jp.nextToken();
								} else if (JumpCommand.KEYWORD.equals(fieldValue)) {
									processJump();
									jp.nextToken();
								} else if (TurnLeftCommand.KEYWORD.equals(fieldValue)) {
									processTurnLeft();
									jp.nextToken();
								} else if (TurnRightCommand.KEYWORD.equals(fieldValue)) {
									processTurnRight();
									jp.nextToken();
								} else if (AssignCommand.KEYWORD.equals(fieldValue)) {
									processAssign();
								} else if (AssignWithAdditionCommand.KEYWORD.equals(fieldValue)) {
									processAssignWithAddition();
								} else if (AssignWithSubtractionCommand.KEYWORD.equals(fieldValue)) {
									processAssignWithSubtraction();
								} else if (IncrementCommand.KEYWORD.equals(fieldValue)) {
									processIncrement();
								} else if (DecrementCommand.KEYWORD.equals(fieldValue)) {
									processDecrement();
								} else if (FunctionCallCommand.KEYWORD.equals(fieldValue)) {
									processFunctionCall();
								} else if (MainBlock.KEYWORD.equals(fieldValue)) {
									processMain();
								} else if (FunctionBlock.KEYWORD.equals(fieldValue)) {
									processFunction();
								} else if (RepeatBlock.KEYWORD.equals(fieldValue)) {
									processRepeat();
								}

							} else {
								throw new ParserException("Oczekiwano typu komendy");
							}
						} else {
							throw new ParserException("Niepoprawna nazwa obiektu");
						}
					} else {
						throw new ParserException("Oczekiwano na nazwe pola");
					}
				} else {
					throw new ParserException("W tablicy mozna zagniezdzac tylko obiekty");
				}

			}

		} else {
			throw new ParserException("Najwyzszy poziom JSONa musi byc tablica");
		}

	}

	private void processFunctionCall() throws JsonParseException, IOException, ParserException {
		FunctionCallCommand functionCall = new FunctionCallCommand();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME && jp.getCurrentName().equals(FunctionCallCommand.NAME_KEYWORD)) {
				if (jp.nextToken() == JsonToken.VALUE_STRING) {
					functionCall.setName(jp.getText());
				} else
					throw new ParserException("Niepoprawna nazwa funkcji");
			} else
				throw new ParserException("Spodziewano sie pola name. Otrzymano: " + jp.getCurrentName());
		}
		addCommandToCurrent(functionCall);
	}

	private void processDecrement() throws JsonParseException, IOException, ParserException {
		DecrementCommand decrement = new DecrementCommand();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME && jp.getCurrentName().equals(DecrementCommand.VARIABLE_KEYWORD)) {
				if (jp.nextToken() == JsonToken.VALUE_STRING) {
					decrement.setVariable(jp.getText());
				} else
					throw new ParserException("Niepoprawna wartosc zmiennej");
			} else
				throw new ParserException("Spodziewano sie pola variable. Otrzymano: " + jp.getCurrentName());
		}
		addCommandToCurrent(decrement);
	}

	private void processIncrement() throws JsonParseException, IOException, ParserException {
		IncrementCommand increment = new IncrementCommand();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME && jp.getCurrentName().equals(IncrementCommand.VARIABLE_KEYWORD)) {
				if (jp.nextToken() == JsonToken.VALUE_STRING) {
					increment.setVariable(jp.getText());
				} else
					throw new ParserException("Niepoprawna wartosc zmiennej");
			} else
				throw new ParserException("Spodziewano sie pola variable. Otrzymano: " + jp.getCurrentName());
		}
		addCommandToCurrent(increment);
	}

	private void processAssignWithSubtraction() throws JsonParseException, IOException, ParserException {
		AssignWithSubtractionCommand assignWithSubtraction = new AssignWithSubtractionCommand();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
				String name = jp.getCurrentName();
				if (AssignWithSubtractionCommand.LEFT_OPERAND_KEYWORD.equals(name)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assignWithSubtraction.setLeftOperand(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc lewego parametru");
				} else if (AssignWithSubtractionCommand.FIRST_RIGHT_OPERAND_KEYWORD.equals(name)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assignWithSubtraction.setFirstRightOperand(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc dla firstRightOperand");
				} else if (AssignWithSubtractionCommand.SECOND_RIGHT_OPERAND_KEYWORD.equals(name)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assignWithSubtraction.setSecondRightOperand(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc dla secondRightOperand");
				} else
					throw new ParserException("Niepoprawne pole: " + name);
			}
		}
		addCommandToCurrent(assignWithSubtraction);
	}

	private void processAssignWithAddition() throws JsonParseException, IOException, ParserException {
		AssignWithAdditionCommand assignWithAddition = new AssignWithAdditionCommand();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
				String name = jp.getCurrentName();
				if (name.equals(AssignWithAdditionCommand.LEFT_OPERAND_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assignWithAddition.setLeftOperand(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc dla: " + name);
				} else if (name.equals(AssignWithAdditionCommand.FIRST_RIGHT_OPERAND_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assignWithAddition.setFirstRightOperand(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc dla: " + name);

				} else if (name.equals(AssignWithAdditionCommand.SECOND_RIGHT_OPERAND_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assignWithAddition.setSecondRightOperand(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc dla: " + name);
				} else
					throw new ParserException("Nieznane pole: " + name);
			}
		}
		addCommandToCurrent(assignWithAddition);
	}

	private void processAssign() throws JsonParseException, IOException, ParserException {
		AssignCommand assign = new AssignCommand();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
				String name = jp.getCurrentName();
				if (name.equals(AssignCommand.LEFT_OPERAND_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assign.setLeftOperand(jp.getText());
					} else
						throw new ParserException("Nieprawidlowa wartosc lewego operandu");
				} else if (name.equals(AssignCommand.RIGHT_OPERAND_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						assign.setRightOperand(jp.getText());
					} else
						throw new ParserException("Nieprawidlowa wartosc prawego operandu");
				} else {
					throw new ParserException("Nieznane pole: " + name);
				}
			} else
				throw new ParserException("Spodziewano sie pola");
		}
		addCommandToCurrent(assign);
	}

	private void processMain() throws ParserException, JsonParseException, IOException {
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME && jp.getCurrentName().equals(MainBlock.COMMANDS_KEYWORD)) {
				//TODO - mozna to zrobic sprytniej przy uzyciu stosu tak jak w CodeParser
				//Nie trzeba bedzie incrementowac commandCountera w tylu miejscach
				List<Command> parent = current;
				MainBlock main = new MainBlock();
				current = new ArrayList<Command>();
				process();
				main.setCommands(current);
				current = parent;
				addCommandToCurrent(main);
			} else {
				throw new ParserException("W main dopuszczalna jest tylko tablica commands");
			}
		}
	}

	private void processFunction() throws JsonParseException, IOException, ParserException {
		FunctionBlock function = new FunctionBlock();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
				if (jp.getCurrentName().equals(FunctionBlock.NAME_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						function.setName(jp.getText());
					} else
						throw new ParserException("Spodziewano sie nazwy funkcji");
				} else if (jp.getCurrentName().equals(FunctionBlock.COMMANDS_KEYWORD)) {
					List<Command> parent = current;
					current = new ArrayList<Command>();
					process();
					function.setCommands(current);
					current = parent;
					addCommandToCurrent(function);
				} else
					throw new ParserException("Nieprawidlowe pole: " + jp.getCurrentName());
			}
		}
	}

	private void processRepeat() throws JsonParseException, IOException, ParserException {
		RepeatBlock repeat = new RepeatBlock();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
				if (jp.getCurrentName().equals(RepeatBlock.COUNT_KEYWORD)) {
					if (jp.nextToken() == JsonToken.VALUE_STRING) {
						repeat.setCount(jp.getText());
					} else
						throw new ParserException("Niepoprawna wartosc dla: " + jp.getCurrentName());
				} else if (jp.getCurrentName().equals(RepeatBlock.COMMANDS_KEYWORD)) {
					List<Command> parent = current;
					current = new ArrayList<Command>();
					process();
					repeat.setCommands(current);
					current = parent;
					addCommandToCurrent(repeat);
				} else
					throw new ParserException("Nieznane pole: " + jp.getCurrentName());

			}
		}

	}

	private void processMove() {
		addCommandToCurrent(new MoveCommand());
	}

	private void processJump() {
		addCommandToCurrent(new JumpCommand());
	}

	private void processTurnLeft() {
		addCommandToCurrent(new TurnLeftCommand());
	}

	private void processTurnRight() {
		addCommandToCurrent(new TurnRightCommand());
	}
	
	private void addCommandToCurrent(Command command){
		commandCounter++;
		current.add(command);
	}

	@Override
	public ParserResult parse(String JSON) throws ParserException {
		try {
			commands = new ArrayList<Command>();
			current = commands;
			JsonFactory jsonFactory = new JsonFactory();
			jp = jsonFactory.createParser(JSON);
			process();
			jp.close();
			return new ParserResult(commandCounter,commands);
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
	}

}
