import 'package:flutter/material.dart';
import 'package:flutter/widget_previews.dart';

class CustomButton extends StatelessWidget {
  const CustomButton({super.key, required this.title, required this.child});
  final String title;
  final Widget child;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 5.0),
      child: FilledButton(
        onPressed: () {
          Navigator.of(context).push(MaterialPageRoute(builder: (_) => child));
        },
        child: Text(title, style: const TextStyle(fontSize: 18)),
      ),
    );
  }
}

@Preview(name: 'Custom Button')
Widget mySampleText() {
  return ElevatedButton(
    onPressed: () {
      print('Hello world');
    },
    child: Text('Hello world'),
  );
}

@Preview(name: 'Custom Button2')
Widget mySampleText2() {
  return FilledButton(
    onPressed: () {
      print('Hello world');
    },
    child: Text('Hello world'),
  );
}
