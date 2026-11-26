# Web Runner

A 2D side-scrolling endless runner written in Java (Swing), built as an OOP course project.

A hero in a red/blue suit runs automatically across city rooftops at night.
Jump over flower tubs and small gaps, duck under antennas and drones, and use
your web to zip across wide gaps from lampposts. The game speeds up over time.
Your score is the distance you run.

## Controls

| Key   | Action                         |
|-------|--------------------------------|
| Space | Jump                           |
| Down  | Duck (hold)                    |
| W     | Web-zip (near a lamppost)      |

## How to run

From the `WebRunner` folder (PowerShell):

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out webrunner.Main
```

## Project structure

```
src/webrunner/
  Main.java      entry point
  core/          window, game loop, game state, input, score
  entity/        player, web meter, lamppost, base classes and interfaces
  obstacle/      obstacles and the obstacle spawner
```

## Team

- Sadia Zohora Faiza
- Sadia Alamgir Ashra
