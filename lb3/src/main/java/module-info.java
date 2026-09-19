module com.lpnu.lb3 {
  requires module.lb2;
  requires com.lpnu.commonutils;

  // Export only top-level API packages; keep implementation packages internal to the module
  exports com.lpnu.alg_lb3;
  exports com.lpnu.alg_lb3.util;
}
