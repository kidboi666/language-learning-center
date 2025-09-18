import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'basic_provider.g.dart';

final counterProvider = StateProvider((ref) {
  ref.onDispose(() {
    print('[counterProvider] onDispose');
  });
  return 0;
});

@Riverpod(keepAlive: true)
String age(Ref ref) {
  final age = ref.watch(counterProvider);
  ref.onDispose(() {
    print('[age] onDispose');
  });
  return 'Hi! I am $age years old.';
}
