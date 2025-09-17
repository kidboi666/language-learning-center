import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'family_provider.g.dart';

// final familyHelloProvider = Provider.family<String, String>((ref, name) {
//   ref.onDispose(() {
//     print('[familyHelloProvider($name)] disposed');
//   });
//   return 'Hello $name';
// });

@riverpod
String familyHello(Ref ref, String name) {
  ref.onDispose(() {
    print('[familyHelloProvider($name)] disposed');
  });
  return 'Hello $name';
}
