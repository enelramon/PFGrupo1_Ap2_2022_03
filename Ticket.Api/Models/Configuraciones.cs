using System.ComponentModel.DataAnnotations;

namespace Ticket.Api.Models
{
    public class Configuraciones
    {
        [Key]
        public int ConfiguracionId { get; set; } = 1;
        public int Theme { get; set; } = 0; //1 = Light, 2= Dark, 0 = System
        public int ColorSchemeIndex { get; set; } = 0;   //0 morado, 1: verde,  2: azul, 3: amarillo, 4: naranja, 5: rojo
    }
}
