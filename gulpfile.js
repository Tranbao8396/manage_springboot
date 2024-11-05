var gulp = require("gulp");
var sass = require("gulp-sass")(require("sass"));
var livereload = require("gulp-livereload");
var sourcemaps = require("gulp-sourcemaps");
var minify = require("gulp-minify");
var minifyCss = require("gulp-clean-css");
var path = require("path");
var rename = require("gulp-rename");
var cache = require("gulp-cached");

gulp.task("sass", function () {
    return gulp.src("./_scss/main.scss")
        .pipe(sourcemaps.init())
        .pipe(sourcemaps.identityMap())
        .pipe(sass())
        .pipe(sourcemaps.write("./"))
        .pipe(gulp.dest(path.join(__dirname, "/src/main/resources/static/css/")))
        .pipe(livereload());
});

gulp.task("minify", function () {
    return gulp.src("./_scss/main.scss")
        .pipe(sass())
        .pipe(minifyCss({compatibility: "ie11"}))
        .pipe(gulp.dest(path.join(__dirname, "/src/main/resources/static/css/")))
        .pipe(livereload());
});

// gulp.task('js', function() {
//   return gulp.src([ "frontend/_js/*.js" ])
//      .pipe(cache('linting'))
//      .pipe(minify({
//         ext: {
//            min: '.min.js'
//         },
//         noSource: true
//      }))
//      // .pipe(rename({ suffix: '.min' }))
//      .pipe(gulp.dest(path.join(__dirname, '/frontend/js/')))
//      .pipe(livereload())
// });

// Watch Files For Changes
gulp.task("watch", function () {
    livereload.listen();
    gulp.watch('./_scss/**', gulp.series('sass'));
    // gulp.watch('./public/frontend/scss/**/**', gulp.series('sass'));
    // gulp.watch([
    //    'frontend/_js/*.js'
    // ], gulp.series('js'));
});