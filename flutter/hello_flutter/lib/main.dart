import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: EdgeInsets.all(8),
          child: ListView(
            children: <Widget>[
              Column(
                spacing: 12,
                children: [
                  const Text('You have pushed the button this many times:'),
                  Text(
                    '$_counter',
                    style: Theme.of(context).textTheme.headlineMedium,
                  ),
                  ElevatedButton(
                    onPressed: _incrementCounter,
                    style: ElevatedButton.styleFrom(
                      elevation: 10,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(12),
                      ),
                      foregroundColor: Colors.white,
                      shadowColor: Colors.black54,
                      backgroundColor: Colors.black,
                    ),
                    child: Text('Plus'),
                  ),
                ],
              ),
              Card(
                elevation: 10,
                child: ListTile(
                  leading: Icon(Icons.home),
                  title: Text('Home'),
                  onTap: _incrementCounter,
                )

              )
            ],
          ),
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        height: 60,
        elevation: 10,
        shape: const CircularNotchedRectangle(),
        child: SizedBox(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Flexible(
                flex: 1,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    IconButton(
                      onPressed: _incrementCounter,
                      icon: Center(child: Icon(Icons.home)),
                      padding: EdgeInsets.symmetric(horizontal: 20),
                    ),
                    IconButton(
                      onPressed: _incrementCounter,
                      icon: Icon(Icons.home),
                      padding: EdgeInsets.symmetric(horizontal: 20),
                    ),
                  ],
                ),
              ),
              SizedBox(width: 60),
              Flexible(
                flex: 1,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    IconButton(
                      onPressed: _incrementCounter,
                      icon: Icon(Icons.home),
                      padding: EdgeInsets.symmetric(horizontal: 20),
                    ),
                    IconButton(
                      onPressed: _incrementCounter,
                      icon: Icon(Icons.home),
                      padding: EdgeInsets.symmetric(horizontal: 20),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        backgroundColor: Colors.black,
        foregroundColor: Colors.white,
        onPressed: _incrementCounter,
        child: const Icon(Icons.add),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }
}
