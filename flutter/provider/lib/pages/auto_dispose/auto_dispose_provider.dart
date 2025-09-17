import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'auto_dispose_provider.g.dart';

// final autoDisposeHelloProvider = Provider.autoDispose<String>((ref) {
//   print('[autoDisposeHelloProvider] created');
//
//   ref.onDispose(() {
//     print('[autoDisposeHelloProvider] disposed');
//   });
//   return 'Hello';
// });

@riverpod
String autoDisposeHello(Ref ref) {
  print('[autoDisposeHello] created');
  ref.onDispose(() {
    print('[autoDisposeHello] disposed');
  });
  return 'Hello';
}
