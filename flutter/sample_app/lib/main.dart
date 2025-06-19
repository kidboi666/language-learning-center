import 'package:flutter/material.dart';

void main() => runApp(WidgetDemo());

class WidgetDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo App',
      initialRoute: '/page1',
      routes: {
        '/page1': (context) => FirstPage(),
        '/page2': (context) => SecondPage(data: '(request)'),
      },
    );
  }
}

class FirstPage extends StatefulWidget {
  @override
  State<FirstPage> createState() => _FirstPageState();
}

class _FirstPageState extends State<FirstPage> {
  var result;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('First Page')),
      body: Column(
        children: <Widget>[
          Center(
            child: ElevatedButton(
              onPressed: () async {
                var newResult = await Navigator.pushNamed(context, '/page2');
                setState(() {
                  result = newResult;
                });
              },
              child: Text('Go to next Page'),
            ),
          ),
          Text('$result'),
        ],
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  final String data;

  SecondPage({required this.data});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('second page')),
      body: Column(
        children: [
          Center(
            child: ElevatedButton(
              onPressed: () {
                Navigator.pop(context, '(result)');
              },
              child: Text('go to previous page'),
            ),
          ),
          Text('$data'),
        ],
      ),
    );
  }
}
