import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'basic_provider.dart';

class BasicPage extends ConsumerWidget {
  const BasicPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // ref.listen(counterProvider, (prev, next) {
    //   if (next >= 3) {
    //     showDialog(
    //       context: context,
    //       builder: (context) {
    //         return AlertDialog(
    //           title: const Text('Warning'),
    //           content: Text('value is $next'),
    //         );
    //       },
    //     );
    //   }
    // });
    // final value = ref.watch(counterProvider);
    final value = ref.watch(ageProvider);

    return Scaffold(
      appBar: AppBar(title: const Text('StateProvider')),
      body: Center(child: Text(value)),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          ref.read(counterProvider.notifier).state =
              ref.read(counterProvider.notifier).state + 0;
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
