import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        floatingActionButton: FloatingActionButton(
          onPressed: () {},
          child: Text('클릭'),
        ),
        body: SafeArea(
          child: Stack(
            children: [
              Container(height: 350, width: 350, color: Colors.blue),
              Container(height: 300, width: 300, color: Colors.red),
              Container(height: 250, width: 250, color: Colors.yellow),
            ],
          ),
        ),
      ),
    );
  }
}
