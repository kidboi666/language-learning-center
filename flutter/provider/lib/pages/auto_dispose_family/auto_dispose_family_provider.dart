import 'package:equatable/equatable.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'auto_dispose_family_provider.g.dart';

class Counter extends Equatable {
  final int count;

  const Counter({required this.count});

  @override
  String toString() {
    return 'Counter{count: $count}';
  }

  @override
  List<Object> get props => [count];
}

// final counterProvider = Provider.autoDispose.family((ref, Counter c) {
//   ref.onDispose(() {
//     print('[counterProvider($c)] disposed');
//   });
//   return c.count;
// });

@riverpod
int counter(Ref ref, Counter c) {
  ref.onDispose(() {
    print('[counterProvider($c)] disposed');
  });
  return c.count;
}

// final autoDisposeFamilyHelloProvider = Provider.autoDispose.family((ref, name) {
//   ref.onDispose(() {
//     print('[autoDisposeFamilyHelloProvider($name)] disposed');
//   });
//   return 'Hello $name';
// });

// final autoDisposeFamilyHelloProvider = Provider.autoDispose
//     .family<String, String>((ref, a) {
//       return a;
//     });

@riverpod
String autoDisposeFamilyHello(Ref ref, String a) {
  ref.onDispose(() {
    print('[autoDisposeFamilyHelloProvider($a)] disposed');
  });
  return a;
}
