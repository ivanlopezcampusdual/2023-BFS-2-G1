import {
  AfterViewInit,
  Component,
  ElementRef,
  Inject,
  Injector,
  OnInit,
  ViewChild,
  ViewEncapsulation,
} from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import {
  AuthService,
  LocalStorageService,
  NavigationService,
} from "ontimize-web-ngx";
import { Observable } from "rxjs";

@Component({
  selector: "login",
  styleUrls: ["./login.component.scss"],
  templateUrl: "./login.component.html",
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit, AfterViewInit {
  @ViewChild("user", { static: false })
  user: ElementRef<HTMLInputElement>;
  @ViewChild("password", { static: false })
  password: ElementRef<HTMLInputElement>;
  @ViewChild("leftEye", { static: false })
  leftEye: ElementRef<HTMLImageElement>;
  @ViewChild("rightEye", { static: false })
  rightEye: ElementRef<HTMLImageElement>;
  private passwordLength = 0;
  private userLength = 0;
  loginForm: FormGroup = new FormGroup({});
  userCtrl: FormControl = new FormControl("", Validators.required);
  pwdCtrl: FormControl = new FormControl("", Validators.required);
  sessionExpired = false;
  protected showPassword = false;
  protected showImage = false;
  protected currentImage: string;
  protected imagePaths: string[] = [
    "../../assets/images/logo/Animacion/login-logo-fr1.png",
    "../../assets/images/logo/Animacion/login-logo-fr2.png",
    "../../assets/images/logo/Animacion/login-logo-fr3.png",
    "../../assets/images/logo/Animacion/login-logo-fr4.png",
    "../../assets/images/logo/Animacion/login-logo-fr5.png",
    "../../assets/images/logo/Animacion/login-logo-fr6.png",
    "../../assets/images/logo/Animacion/login-logo-fr7.png",
    "../../assets/images/logo/Animacion/login-logo-fr8.png",
    "../../assets/images/logo/Animacion/login-logo-fr9.png",
  ];
  protected reverseImage: string[] = [
    "../../assets/images/logo/Animacion/login-logo-fr9.png",
    "../../assets/images/logo/Animacion/login-logo-fr8.png",
    "../../assets/images/logo/Animacion/login-logo-fr7.png",
    "../../assets/images/logo/Animacion/login-logo-fr6.png",
    "../../assets/images/logo/Animacion/login-logo-fr5.png",
    "../../assets/images/logo/Animacion/login-logo-fr4.png",
    "../../assets/images/logo/Animacion/login-logo-fr3.png",
    "../../assets/images/logo/Animacion/login-logo-fr2.png",
    "../../assets/images/logo/Animacion/login-logo-fr1.png",
  ];
  protected timeoutId;
  protected currentImageIndex: number = 0;
  router: Router;

  constructor(
    private actRoute: ActivatedRoute,
    router: Router,
    @Inject(NavigationService) public navigation: NavigationService,
    @Inject(AuthService) private authService: AuthService,
    @Inject(LocalStorageService) private localStorageService,
    public injector: Injector
  ) {
    this.router = router;

    const qParamObs: Observable<any> = this.actRoute.queryParams;
    qParamObs.subscribe((params) => {
      if (params) {
        const isDetail = params["session-expired"];
        if (isDetail === "true") {
          this.sessionExpired = true;
        } else {
          this.sessionExpired = false;
        }
      }
    });
  }
  buttonTerms() {
    this.router.navigate(["../login/Privacy-Policy"], {
      relativeTo: this.actRoute,
    });
  }
  ngAfterViewInit(): void {
    //PASSWORD

    this.password.nativeElement.addEventListener("input", (event) => {
      if (this.showPassword) {
        this.passwordLength = this.password.nativeElement.value.length;
      } else {
        this.passwordLength = 0;
      }
      this.updateEyeTransforms(this.passwordLength);
    });
    this.password.nativeElement.addEventListener("focus", (event) => {
      if (this.showPassword) {
        this.passwordLength = this.password.nativeElement.value.length;
      } else {
        //console.log("this.showImage: " + this.showImage);
        this.currentImageIndex = 0;
        this.updateImage();
        this.showImage = true;
        this.passwordLength = 0;
      }

      this.updateEyeTransforms(this.passwordLength);
    });

    //USER
    this.user.nativeElement.addEventListener("input", (event) => {
      this.userLength = this.user.nativeElement.value.length;
      this.resetEyeTransform();
      this.updateEyeTransforms(this.userLength);
    });

    this.user.nativeElement.addEventListener("focus", (event) => {
      this.userLength = this.user.nativeElement.value.length;
      this.updateEyeTransforms(this.userLength);
    });
    this.password.nativeElement.addEventListener("blur", () => {
      //console.log("this.showImage: " + this.showImage);
      if (this.showPassword) {
        this.resetEyeTransform();
      } else {
        this.currentImageIndex = 0;
        this.reverseAnimation();
      }
    });

    this.user.nativeElement.addEventListener("blur", () => {
      this.resetEyeTransform();
    });
  }

  updateEyeTransforms(length) {
    let startPosition = -4;
    const maxEyeX = 3 * 0.6; // Establece el límite máximo para la transformación

    const leftEyeX = Math.min(startPosition + length * 0.6, maxEyeX);
    const rightEyeX = Math.min(startPosition + length * 0.6, maxEyeX);
    this.leftEye.nativeElement.style.transform = `translateX(${leftEyeX}px)`;
    this.rightEye.nativeElement.style.transform = `translateX(${rightEyeX}px)`;
  }
  resetEyeTransform() {
    // Restablecer la transformación de los ojos a su posición inicial
    this.leftEye.nativeElement.style.transform = "translateX(0)";
    this.rightEye.nativeElement.style.transform = "translateX(0)";
  }

  ngOnInit(): any {
    this.navigation.setVisible(false);

    this.loginForm.addControl("username", this.userCtrl);
    this.loginForm.addControl("password", this.pwdCtrl);
    if (this.authService.isLoggedIn()) {
      this.router.navigate(["../"], { relativeTo: this.actRoute });
    } else {
      this.authService.clearSessionData();
    }
  }

  login() {
    const userName = this.loginForm.value.username;
    const password = this.loginForm.value.password;
    if (userName && userName.length > 0 && password && password.length > 0) {
      const self = this;
      this.authService.login(userName, password).subscribe(() => {
        self.sessionExpired = false;
        self.router.navigate(["../"], { relativeTo: this.actRoute });
      }, this.handleError);
    }
  }

  handleError(error) {
    switch (error.status) {
      case 401:
        console.error("Email or password is wrong.");
        break;
      default:
        break;
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  updateImage() {
    this.currentImage = this.imagePaths[this.currentImageIndex];
    this.currentImageIndex =
      (this.currentImageIndex + 1) % this.imagePaths.length;

    if (this.currentImageIndex === 0) {
      // Cuando llega al final del arreglo, mantén el índice en la última imagen
      this.currentImageIndex = this.imagePaths.length - 1;
      clearTimeout(this.timeoutId);
    } else {
      this.timeoutId = setTimeout(() => this.updateImage(), 60);
    }
  }
  reverseAnimation() {
    this.currentImage = this.reverseImage[this.currentImageIndex];
    // Usar el arreglo reverseImage en lugar de imagePaths
    this.currentImageIndex =
      (this.currentImageIndex + 1) % this.reverseImage.length;

    if (this.currentImageIndex === 0) {
      this.currentImageIndex = this.reverseImage.length - 1;
      clearTimeout(this.timeoutId);
      this.showImage = false;
    } else {
      this.timeoutId = setTimeout(() => this.reverseAnimation(), 60);
    }
  }
}
