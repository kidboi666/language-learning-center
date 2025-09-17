// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'auto_dispose_provider.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint, type=warning

@ProviderFor(autoDisposeHello)
const autoDisposeHelloProvider = AutoDisposeHelloProvider._();

final class AutoDisposeHelloProvider
    extends $FunctionalProvider<String, String, String>
    with $Provider<String> {
  const AutoDisposeHelloProvider._()
    : super(
        from: null,
        argument: null,
        retry: null,
        name: r'autoDisposeHelloProvider',
        isAutoDispose: true,
        dependencies: null,
        $allTransitiveDependencies: null,
      );

  @override
  String debugGetCreateSourceHash() => _$autoDisposeHelloHash();

  @$internal
  @override
  $ProviderElement<String> $createElement($ProviderPointer pointer) =>
      $ProviderElement(pointer);

  @override
  String create(Ref ref) {
    return autoDisposeHello(ref);
  }

  /// {@macro riverpod.override_with_value}
  Override overrideWithValue(String value) {
    return $ProviderOverride(
      origin: this,
      providerOverride: $SyncValueProvider<String>(value),
    );
  }
}

String _$autoDisposeHelloHash() => r'46cab7443b53311195c6a7d6f71de406a0116fd6';
