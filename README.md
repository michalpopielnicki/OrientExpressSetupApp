# Orient Express Setup – Android app

The Horror on the Orient Express setup randomizer from orient-express-setup.com, extended with the
**Consumed by the Void** expansion and packaged as an Android app.

## What was added

- A new expansion checkbox, **Consumed by the Void** (`consumeByTheVoid`), on the setup screen.
- Two new characters, available as investigators and suspects when the expansion is ticked:
  - Rada Rokić, Photographer (`rada-rokic`)
  - Ambroz Andracek, Detective (`ambroz-andracek`)
- The investigator pool count and the "choices per player" limit include the two detectives.
- The final summary lists the expansion when it was used.

To try the web version on a computer, open `www/index.html` in any browser.

## Getting the APK (no Android Studio needed)

GitHub builds the APK for you in about 5 minutes.

1. Create a free account at github.com, then create a **new repository** (private is fine).
2. On the empty repository page, click **uploading an existing file** and drag in **everything from
   this folder** (not the folder itself). Click **Commit changes**.
3. Check that the file `.github/workflows/build-apk.yml` appears in the repository. Some browsers skip
   folders whose names start with a dot. If it's missing, click **Add file → Create new file**, type
   `.github/workflows/build-apk.yml` as the name, paste in the contents of that file, and commit.
4. Open the **Actions** tab. The "Build Android APK" run starts automatically after each commit
   (you can also start it by hand with **Run workflow**). Wait for the green tick.
5. Click the finished run and download **orient-express-setup-apk** under *Artifacts*. It's a zip
   containing `orient-express-setup.apk`.

## Installing on your phone

1. Copy `orient-express-setup.apk` to the phone (or download it there directly).
2. Tap it. Android will ask you to allow installs from that app (Files, Chrome…); allow it once.
3. Tap **Install**. Google Play Protect may warn that the app is from an unknown developer; choose
   *Install anyway*.

Every build uses the same signing key (`signing/debug.keystore`), so a newer APK installs over the
old one without uninstalling.

## Notes

- The app works offline. The two web fonts load when the phone has internet; offline it uses
  similar built-in fonts.
- The phone's Back button/gesture goes back one screen in the randomizer. On the first screen it
  sends the app to the background.
- The new detectives show initials instead of a portrait, the same as every other character in the
  original site.

## Building locally instead (optional)

Requires Node.js 20+, JDK 21 and Android Studio.

```
npm install
npx cap add android
```

Then apply the overrides the same way the workflow does (copy `android-overrides/res/*` into
`android/app/src/main/res/` after deleting the `mipmap-*` folders and `splash.*` files, and copy
`android-overrides/MainActivity.java` over the generated one), run `npx cap sync android`, and open
the `android` folder in Android Studio.

## Project layout

| Path | Purpose |
| --- | --- |
| `www/` | The web app (patched JS bundle, original CSS, `index.html`) |
| `capacitor.config.json` | App name and id (`com.orientexpress.setup`) |
| `android-overrides/` | App icon, dark theme and splash screen, Back-button handling |
| `signing/debug.keystore` | Fixed signing key so updates install over older builds |
| `.github/workflows/build-apk.yml` | The GitHub build that produces the APK |
